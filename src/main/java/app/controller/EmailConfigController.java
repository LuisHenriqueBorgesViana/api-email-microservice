package app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.model.ServerSmtpModel;

import lombok.Data;

@Data
public class EmailConfigController {
	
	private static final Logger Logger = LoggerFactory.getLogger(EmailConfigController.class);		
	
	private static int    Port     = 465;
	private static String Host     = "smtp.gmail.com";
	private static String Auth     = "true";
	private static String Ssl      = "true";
	private static String User     = "email@gmail.com";
	private static String Password = "qohouvxztheemqyp";

	public ServerSmtpModel getConfigServerSmtp() {
		
		ServerSmtpModel SmptServer = new ServerSmtpModel();
		
		SmptServer.setPortSmtp(Port);
		SmptServer.setHostSmtp(Host);
		SmptServer.setAuthSmtp(Auth);
		SmptServer.setSslSmtp(Ssl);
		SmptServer.setUserSmtp(User);
		SmptServer.setUserPassword(Password);
		
		Logger.info("SMTP Server settings have been loaded.");						
		
		return SmptServer;
	}
}
