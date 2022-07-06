package app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerUtil extends WebMvcConfigurationSupport {

	private static final Logger loggerFactory = LoggerFactory.getLogger(SwaggerUtil.class);

	@Bean
	public Docket greetingApi() {
		
		loggerFactory.info("[EmailService] - The Documentation Query was performed through the Swagger API.");				
		
		return new Docket(DocumentationType.SWAGGER_2)
				   .select()
				   .apis(RequestHandlerSelectors.basePackage("app.service"))
				   .build()
				   .apiInfo(metaData());
	}

	private ApiInfo metaData() {

		return new ApiInfoBuilder()
				   .title("Microservice for Sending Email")
				   .description("Microservice for Sending and Receiving Emails")
				   .version("1.0.0")
				   .license("Apache License Version 2.0")
				   .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
				   .contact(new Contact("Luis Henrique Borges Viana", "https://www.luishenriqueborgesviana.com/", null))
				   .build();
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}