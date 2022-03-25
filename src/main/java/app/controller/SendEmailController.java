package app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.model.ReplyShippinglEmailModel;
import app.util.EmailUtil;

public class SendEmailController {
	
	private static final Logger Logger = LoggerFactory.getLogger(SendEmailController.class);	
	
	EmailUtil UtilEmail = new EmailUtil();	
	
	public ReplyShippinglEmailModel sendEmailPlainTextController(String Recipient, String Subject, String Content) {
		
		Logger.info("Started Execution of Text Email Send Controller.");		
		
		return UtilEmail.sendEmailPlainText(Recipient, Subject, Content);
	}
}

