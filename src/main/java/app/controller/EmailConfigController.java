package app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.model.ServerSmtpModel;

public class EmailConfigController {
	
	private static final Logger Logger = LoggerFactory.getLogger(EmailConfigController.class);		

	public ServerSmtpModel getConfigServerSmtp() {
		
		ServerSmtpModel SmptServer = new ServerSmtpModel();
		
		SmptServer.setPortSmtp(465);
		SmptServer.setHostSmtp("smtp.gmail.com");
		SmptServer.setAuthSmtp("true");
		SmptServer.setSslSmtp("true");
		SmptServer.setUserSmtp("email@gmail.com");
		SmptServer.setUserPassword("qohouvxztheemqyp");
		
		Logger.info("SMTP Server settings have been loaded.");						
		
		return SmptServer;
	}
}
