package app.service;

import app.controller.SendEmailController;

import app.model.ReplyShippinglEmailModel;
import app.model.SendEmailModel;

import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/send")
@Api(value = "Send Email", description = "Endpoints to Send Emails to a Recipient", tags = "Sending")
public class SendEmailService {

	private static final Logger Logger = LoggerFactory.getLogger(SendEmailService.class);

	SendEmailController EmailSenderController = new SendEmailController();

	@RequestMapping(value = "/text", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Send Email with Plain Text Content", response = ReplyShippinglEmailModel.class, tags = "Sending")
	public ReplyShippinglEmailModel sendTextMail(@RequestBody SendEmailModel Email) {

		Logger.info("Started Sending Email Through Endpoint, through endpoint: [/send/text].");		

		return EmailSenderController.sendEmailPlainTextController(Email.getRecipientEmail(), Email.getSubjectEmail(), Email.getContentEmail());
	}
}
