package app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.controller.EmailConfigController;

import app.model.ServerSmtpModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/config")
@Api(value = "Configuration Email", description = "Endpoints Managing current Email Service Configuration", tags = "Configuration")
public class ConfigurationServiceEmail {
	
	private static final Logger Logger = LoggerFactory.getLogger(SendEmailService.class);	
	
	EmailConfigController ConfigController = new EmailConfigController();

	@RequestMapping(value = "/smtp", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Returns the Current Email Service Configuration", response = ServerSmtpModel.class, tags = "Configuration")
	public ServerSmtpModel getConfigurationEmailService() {

		Logger.info("Started Loading the SMTP Service Configuration, through endpoint: [/config/smtp].");

		return ConfigController.getConfigServerSmtp();
	}
}
