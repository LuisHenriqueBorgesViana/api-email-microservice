package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MailServiceApplication {
	
	private static final Logger loggerFactory = LoggerFactory.getLogger(MailServiceApplication.class);

	public static void main(String[] args) {
		
		SpringApplication.run(MailServiceApplication.class, args);
		
		loggerFactory.info("Microservice started successfully.");	
		
		
	}
}
