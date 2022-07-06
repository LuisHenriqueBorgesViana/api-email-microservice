package app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SendEmailModel {
	
	@JsonProperty("recipientEmail")
	@ApiModelProperty(notes = "Recipient Email", name = "Recipient Shipping", required = true, value = "Email that Will Receive the Message", example= "luishenriqueborgesviana@outlook.com", position = 0)
	private String recipientEmail;
	
	@JsonProperty("subjectEmail")
	@ApiModelProperty(notes = "Subject Email", name = "Subject Email", required = true, value = "Email Title Subject", example= "Test Email", position = 1)	
	private String subjectEmail;
	
	@JsonProperty("contentEmail")
	@ApiModelProperty(notes = "Content Email", name = "Content Email", required = true, value = "Email Text Content", example= "Email Content", position = 2)		
	private String contentEmail;	
}
