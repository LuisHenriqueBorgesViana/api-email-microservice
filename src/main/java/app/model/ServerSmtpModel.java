package app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ServerSmtpModel {

	@JsonProperty("portSmtp")
	@ApiModelProperty(notes = "Port Smtp", name = "Port Smtp", required = true, value = "Service Port", example = "465", position = 0)
	private int portSmtp;
	
	@JsonProperty("hostSmtp")
	@ApiModelProperty(notes = "Host Smtp", name = "Host Smtp", required = true, value = "SMTP Service Host", example = "smtp.gmail.com", position = 1)	
	private String hostSmtp;

	@JsonProperty("authSmtp")
	@ApiModelProperty(notes = "Host Smtp", name = "Host Smtp", required = true, value = "Set if Service Auth is active", example = "true", position = 2)		
	private String authSmtp;
	
	@JsonProperty("sslSmtp")
	@ApiModelProperty(notes = "Ssl Smtp", name = "Host Smtp", required = true, value = "Set if Service SSL is Enabled", example = "true", position = 3)		
	private String sslSmtp;
	
	@JsonProperty("userSmtp")
	@ApiModelProperty(notes = "User Smtp", name = "User Smtp", required = true, value = "Service Access and Authentication User", example = "email@gmail.com", position = 4)		
	private String userSmtp;

	@JsonProperty("userPassword")
	@ApiModelProperty(notes = "User Password", name = "User Password", required = true, value = "Service Authentication User Access Password", example = "qohouvxztheemqypx", position = 5)			
	private String userPassword;	
}
