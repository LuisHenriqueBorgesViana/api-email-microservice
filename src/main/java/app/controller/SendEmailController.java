package app.controller;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.model.EmailModel;
import app.model.ReplyShippinglEmailModel;

import app.util.EmailUtil;

public class SendEmailController {
	
	private static final Logger loggerFactory = LoggerFactory.getLogger(SendEmailController.class);	
		
	public ReplyShippinglEmailModel sendEmailController(EmailModel emailObject) {
	
		EmailUtil utilEmail = new EmailUtil();	
				
		loggerFactory.info("Started Execution of Text Email Send Controller.");		
								
		ReplyShippinglEmailModel shippinglEmail = validateValuesObjectEmail(emailObject);		
		
		if(!shippinglEmail.isShippingStatus()) {
			
			return shippinglEmail;
		} 
		
		return utilEmail.sendEmail(emailObject);
	}
	
	private ReplyShippinglEmailModel validateValuesObjectEmail(EmailModel emailObject) {

		loggerFactory.info("Started Execution of Text Email Send Controller.");				
		
		ReplyShippinglEmailModel replyShippinglEmail = new ReplyShippinglEmailModel();
		
		return replyShippinglEmail;
	}
}

