package com.prodian.task;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
@EnableEncryptableProperties
public class EmployeeTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeTaskApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration() {

		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.ant("/task/employee/*"))
				.apis(RequestHandlerSelectors.basePackage("com.prodian")).build().apiInfo(apiDetails());

	}

	private ApiInfo apiDetails() {

		return new ApiInfo("Aravindharaj P", "Task from Prodian", "2.0", "Free to Use",
				new springfox.documentation.service.Contact("Aravindharaj", "www.arav.com",
						"aravindharaj1999@gmail.com"),
				"Api License", "www.arav.com", Collections.emptyList());

	}

}
