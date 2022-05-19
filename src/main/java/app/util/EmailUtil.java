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
		
		ReplyShippinglEmailModel replyShippingl = new ReplyShippinglEmailModel();
		
		Logger.info("Getting the SMTP Server Configuration Properties.");				
		
		EmailConfigController configEmailController = new EmailConfigController();
						
		ServerSmtpModel smptServer = configEmailController.getConfigServerSmtp();
		
		Logger.info("Creating Session on SMTP " + "[Host: " + smptServer.getHostSmtp() + ", Port: " + smptServer.getPortSmtp() + "].");	
		
        Properties systemProperties = System.getProperties();
        systemProperties.setProperty("mail.smtp.host", smptServer.getHostSmtp());
        systemProperties.put("mail.smtp.port", smptServer.getPortSmtp()); 
        systemProperties.put("mail.smtp.auth", smptServer.getAuthSmtp());
        systemProperties.put("mail.smtp.ssl.enable", smptServer.getSslSmtp());
        		
		Logger.info("Authenticating to the Remote SMTP Server [User: " +  smptServer.getUserSmtp() + ", Password: " + smptServer.getUserPassword() + "].");		
		
		String userAuthentication     = smptServer.getUserSmtp();
		String passwordAuthentication = smptServer.getUserPassword();

        Session sessionMail = Session.getInstance(systemProperties, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() { 
                
                return new PasswordAuthentication(userAuthentication, passwordAuthentication); 
            }
        });
        
        try {
        
            Logger.info("Starting Email Message Creation.");		
        	
			MimeMessage messageEmail = new MimeMessage(sessionMail);
			messageEmail.setFrom(new InternetAddress(smptServer.getUserSmtp()));
			messageEmail.addRecipient(Message.RecipientType.TO, new InternetAddress(Recipient));
			messageEmail.setSubject(Subject, "UTF-8");
			messageEmail.setContent(Content, "text/html;charset=UTF-8");
            
            Logger.info("Email Ready to Send.");	

            Transport.send(messageEmail); 
            
            Logger.info("Email Successfully Sent to [Recipient: " + Recipient + ", Subject: " + Subject + ", Content: " + Content + "].");		            
            
        	replyShippingl.setShippingStatus(true);
        	replyShippingl.setRecipientShipping(Recipient);
        	replyShippingl.setSubjectShipping(Subject);
        	replyShippingl.setContentShipping(Content);
        	replyShippingl.setReplyShipping("Sending the Email Successfully");
                        
        } catch (Error | MessagingException errorSendMail) {
        	
            Logger.error("Error Occurred While Sending Email to Recipient.");		
        	
        	replyShippingl.setShippingStatus(false);
        	replyShippingl.setRecipientShipping(Recipient);
        	replyShippingl.setSubjectShipping(Subject);
        	replyShippingl.setContentShipping(Content);
        	replyShippingl.setReplyShipping(ExceptionUtils.getRootCauseMessage(errorSendMail));            
        }
        
        return replyShippingl;
	}
}
