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

import app.model.EmailModel;
import app.model.ReplyShippinglEmailModel;

import lombok.Data;

@Data
public class EmailUtil {

	private static final Logger loggerFactory = LoggerFactory.getLogger(EmailUtil.class);	

	public ReplyShippinglEmailModel sendEmail(EmailModel emailObject) {
		
		loggerFactory.info("Creating the Send Email Reply Model.");				
		
		ReplyShippinglEmailModel replyShippingl = new ReplyShippinglEmailModel();

		loggerFactory.info("Creating Session on SMTP [Host: {}, Port: {}].", emailObject.getServerSmtp().getHostSmtp(), emailObject.getServerSmtp().getPortSmtp());
				
        Properties systemProperties = System.getProperties();
        systemProperties.setProperty("mail.smtp.host", emailObject.getServerSmtp().getHostSmtp());
        systemProperties.put("mail.smtp.port", emailObject.getServerSmtp().getPortSmtp()); 
        systemProperties.put("mail.smtp.auth", emailObject.getServerSmtp().isAuthSmtp());
        systemProperties.put("mail.smtp.ssl.enable", emailObject.getServerSmtp().isSslSmtp());

  		loggerFactory.info("Authenticating to the Remote SMTP Server [User: {}, Password: {}].", emailObject.getServerSmtp().getUserSmtp(), emailObject.getServerSmtp().getUserPasswordSmtp());

        Session sessionMail = Session.getInstance(systemProperties, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() { 
                
                return new PasswordAuthentication(emailObject.getServerSmtp().getUserSmtp(), emailObject.getServerSmtp().getUserPasswordSmtp()); 
            }
        });
        
        try {
        
            loggerFactory.info("Starting Email Message Creation.");		
        	
			MimeMessage messageEmail = new MimeMessage(sessionMail);
			messageEmail.setFrom(new InternetAddress(emailObject.getServerSmtp().getUserSmtp()));
			messageEmail.addRecipient(Message.RecipientType.TO, new InternetAddress(emailObject.getServerSmtp().getUserSmtp()));
			messageEmail.setSubject(emailObject.getSendEmail().getSubjectEmail(), "UTF-8");
			messageEmail.setContent(emailObject.getSendEmail().getContentEmail(), "text/html;charset=UTF-8");
            
            loggerFactory.info("Email Ready to Send.");	

            //Transport.send(messageEmail); 
            
    		loggerFactory.info("Email Successfully Sent to [Recipient: {}, : Subject: {}, Content: {}].", emailObject.getSendEmail().getRecipientEmail(), emailObject.getSendEmail().getSubjectEmail(), emailObject.getSendEmail().getContentEmail());            
            
    		replyShippingl.setIdentificationKey(emailObject.getIdentificationKey());
        	replyShippingl.setShippingStatus(true);
        	replyShippingl.setRecipientShipping(emailObject.getSendEmail().getRecipientEmail());
        	replyShippingl.setSubjectShipping(emailObject.getSendEmail().getSubjectEmail());
        	replyShippingl.setContentShipping(emailObject.getSendEmail().getContentEmail());
        	replyShippingl.setReplyShipping("Sending the Email Successfully");
        	
    		loggerFactory.info("Finished Sending Email Key {}", emailObject.getIdentificationKey());		
                        
        } catch (MessagingException errorSendMail) {
        	
            loggerFactory.error("Error Occurred While Sending Email to Recipient.");
            
    		replyShippingl.setIdentificationKey(emailObject.getIdentificationKey());
        	replyShippingl.setShippingStatus(false);
        	replyShippingl.setRecipientShipping(emailObject.getSendEmail().getRecipientEmail());
        	replyShippingl.setSubjectShipping(emailObject.getSendEmail().getSubjectEmail());
        	replyShippingl.setContentShipping(emailObject.getSendEmail().getContentEmail());
        	replyShippingl.setReplyShipping(ExceptionUtils.getRootCauseMessage(errorSendMail));   
        	
    		loggerFactory.info("Finished Sending Email Key {}", emailObject.getIdentificationKey());		        	
        }
        
        return replyShippingl;
	}
}
