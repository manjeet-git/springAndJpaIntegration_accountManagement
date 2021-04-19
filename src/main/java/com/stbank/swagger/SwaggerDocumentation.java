package com.stbank.swagger;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerDocumentation {

	@Bean
	public Docket swaggerDocumentationDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/stbank/account/**"))
				.apis(RequestHandlerSelectors.basePackage("com.stbank.controller"))
				.build()
				.apiInfo(getApiInformation());
	}
	
	public ApiInfo getApiInformation() {
		return new ApiInfo("Account Management Documentation",
				           "API to Perform convenient operation to manage the Accounts of Employees.",
				           "2.4.0",
				            "Free to install", 
				           new Contact("Manjeet","https://www.google.com", "mkmanjeetkumarbgs@gmail.com"),
				           "Api Licence",
				           "http://www.swagger-ui.com",
				            new ArrayList()
				           );
	}
}
