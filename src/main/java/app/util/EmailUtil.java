package app.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.exception.ExceptionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.controller.EmailConfigController;

import app.model.ReplyShippinglEmailModel;
import app.model.ServerSmtpModel;

import lombok.Data;

@Data
public class EmailUtil {

	private static final Logger Logger = LoggerFactory.getLogger(EmailUtil.class);	

	public ReplyShippinglEmailModel sendEmailPlainText(String Recipient, String Subject, String Content) {
		
		Logger.info("Creating the Send Email Reply Model.");				
		
		ReplyShippinglEmailModel ReplyShippingl = new ReplyShippinglEmailModel();
		
		Logger.info("Getting the SMTP Server Configuration Properties.");				
		
		EmailConfigController ConfigEmailController = new EmailConfigController();
		
		ServerSmtpModel SmptServer = new ServerSmtpModel();
				
		SmptServer = ConfigEmailController.getConfigServerSmtp();
		
		Logger.info("Creating Session on SMTP " + "[Host: " + SmptServer.getHostSmtp() + ", Port: " + SmptServer.getPortSmtp() + "].");	
		
        Properties Propriedades = System.getProperties();
        Propriedades.setProperty("mail.smtp.host", SmptServer.getHostSmtp());
        Propriedades.put("mail.smtp.port", SmptServer.getPortSmtp()); 
        Propriedades.put("mail.smtp.auth", SmptServer.getAuthSmtp());
        Propriedades.put("mail.smtp.ssl.enable", SmptServer.getSslSmtp());
        		
		Logger.info("Authenticating to the Remote SMTP Server [User: " +  SmptServer.getUserSmtp() + ", Password: " + SmptServer.getUserPassword() + "].");		
		
		String UserAuthentication     = SmptServer.getUserSmtp();
		String PasswordAuthentication = SmptServer.getUserPassword();

        Session SessionEmail = Session.getInstance(Propriedades, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() { 
                
                return new PasswordAuthentication(UserAuthentication, PasswordAuthentication); 
            }
        });
        
        try {
        
            Logger.info("Starting Email Message Creation.");		
        	
			MimeMessage MessageEmail = new MimeMessage(SessionEmail);
			MessageEmail.setFrom(new InternetAddress(SmptServer.getUserSmtp()));
			MessageEmail.addRecipient(Message.RecipientType.TO, new InternetAddress(Recipient));
			MessageEmail.setSubject(Subject, "UTF-8");
			MessageEmail.setContent(Content, "text/html;charset=UTF-8");
            
            Logger.info("Email Ready to Send.");	

            Transport.send(MessageEmail); 
            
            Logger.info("Email Successfully Sent to [Recipient: " + Recipient + ", Subject: " + Subject + ", Content: " + Content + "].");		            
            
        	ReplyShippingl.setShippingStatus(true);
        	ReplyShippingl.setRecipientShipping(Recipient);
        	ReplyShippingl.setSubjectShipping(Subject);
        	ReplyShippingl.setContentShipping(Content);
        	ReplyShippingl.setReplyShipping("Sending the Email Successfully");
                        
        } catch (Error | MessagingException Error) {
        	
            Logger.error("Error Occurred While Sending Email to Recipient.");		
        	
        	ReplyShippingl.setShippingStatus(false);
        	ReplyShippingl.setRecipientShipping(Recipient);
        	ReplyShippingl.setSubjectShipping(Subject);
        	ReplyShippingl.setContentShipping(Content);
        	ReplyShippingl.setReplyShipping(ExceptionUtils.getRootCauseMessage(Error));            
        }
        
        return ReplyShippingl;
	}
}
