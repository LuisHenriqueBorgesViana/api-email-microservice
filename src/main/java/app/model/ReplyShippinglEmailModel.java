package app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

@Data
@ApiModel
public class ReplyShippinglEmailModel {
	
	@JsonProperty("identificationKey")
	@ApiModelProperty(notes = "Identification Key", name = "Identification Key", required = true, value = "Send Key that uniquely identifies the email", example= "EMAIL-123", position = 0)
	private String identificationKey;	
	
	@JsonProperty("shippingStatus")
	@ApiModelProperty(notes = "Shipping Status", name = "Shipping Status", required = true, value = "Identifies whether the Email was Sent or Not", example= "true", position = 0)
	private boolean shippingStatus;

	@JsonProperty("recipientShipping")
	@ApiModelProperty(notes = "Recipient Shipping", name = "Recipient Shipping", required = true, value = "Email Sending Recipient", example= "luishenriqueborgesviana@outlook.com", position = 1)	
	private String recipientShipping;

	@JsonProperty("subjectShipping")
	@ApiModelProperty(notes = "Subject Shipping", name = "Subject Shipping", required = true, value = "Sent Email Subject", example= "Test Send Email", position = 2)		
	private String subjectShipping;

	@JsonProperty("contentShipping")
	@ApiModelProperty(notes = "Content Shipping", name = "Content Shipping", required = true, value = "Text Content of Sent Email", example= "Test Email Content Sent", position = 3)			
	private String contentShipping;

	@JsonProperty("replyShipping")
	@ApiModelProperty(notes = "Reply Shipping", name = "Reply Shipping", required = true, value = "Send Email Procedure Service Response", example= "Sending the Email Successfully", position = 4)		
    private String replyShipping;
}