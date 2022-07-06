package app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

@Data
@ApiModel
public class EmailModel {
	
	@JsonProperty("identificationKey")
	@ApiModelProperty(notes = "Identification Key", name = "Identification Key", required = true, value = "Send Key that uniquely identifies the email", example= "EMAIL-123", position = 0)
	private String identificationKey;

	@JsonProperty("serverSmtp")
	@ApiModelProperty(notes = "SMTP Server Model", name = "SMTP Server Model", required = true, value = "SMTP Server Template containing all Attributes", example= "Model", position = 1)		
	private ServerSmtpModel serverSmtp;

	@JsonProperty("sendEmail")
	@ApiModelProperty(notes = "Email Sending Object", name = "Email Sending Object", required = true, value = "Email Sending Object containing all Attributes", example= "Model", position = 2)	
    private SendEmailModel sendEmail;
}