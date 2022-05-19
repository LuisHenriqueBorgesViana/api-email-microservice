package app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.model.ServerSmtpModel;

import lombok.Data;

@Data
public class EmailConfigController {
	
	private static final Logger Logger = LoggerFactory.getLogger(EmailConfigController.class);		
	
	private static int    portSmtp     = 465;
	private static String hostSmtp     = "smtp.gmail.com";
	private static String authSmtp     = "true";
	private static String sslSmtp      = "true";
	private static String userSmtp     = "email@gmail.com";
	private static String passwordSmtp = "qohouvxztheemqyp";

	public ServerSmtpModel getConfigServerSmtp() {
		
		ServerSmtpModel smptServer = new ServerSmtpModel();
		
		smptServer.setPortSmtp(portSmtp);
		smptServer.setHostSmtp(hostSmtp);
		smptServer.setAuthSmtp(authSmtp);
		smptServer.setSslSmtp(sslSmtp);
		smptServer.setUserSmtp(userSmtp);
		smptServer.setUserPassword(passwordSmtp);
		
		Logger.info("SMTP Server settings have been loaded.");						
		
		return smptServer;
	}
}
