package app.service;

import app.controller.SendEmailController;
import app.model.EmailModel;
import app.model.ReplyShippinglEmailModel;

import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import lombok.NonNull;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/send")
@Api(value = "Send Email", tags = "Sending")
public class SendEmailService {

	private static final Logger loggerFactory = LoggerFactory.getLogger(SendEmailService.class);
	
	@PostMapping(value = "/email", produces = "application/json")
	@ApiOperation(value = "Send Email with Plain Text Content", response = ReplyShippinglEmailModel.class, tags = "Sending")
	public ReplyShippinglEmailModel sendEmailService(@NonNull @RequestBody EmailModel emailObject) {

		SendEmailController emailSenderController = new SendEmailController();

		loggerFactory.info("Started Sending Email Key {}", emailObject.getIdentificationKey());		

		return emailSenderController.sendEmailController(emailObject);
	}
}
