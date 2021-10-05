package com.greglturnquist.hateaoasdemo.webclient;

import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.HypermediaWebClientConfigurer;
import org.springframework.hateoas.server.core.TypeReferences.EntityModelType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableHypermediaSupport(type = { HAL, HAL_FORMS })
public class WebClientApplication {

	private static final Logger logger = LoggerFactory.getLogger(WebClientApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WebClientApplication.class, args);
	}

	@Bean
	WebClientCustomizer customize(HypermediaWebClientConfigurer configurer) {
		return webClientBuilder -> configurer.registerHypermediaTypes(webClientBuilder);
	}

	@Bean
	CommandLineRunner talktoServer(WebClient.Builder builder) {
		return args -> {
			WebClient client = builder.build();

			EntityModel<FlexibleEmployee> employeeModel = //
					client.get().uri("http://localhost:8080/rest/employees/0") //
							.accept(MediaTypes.HAL_JSON) //
							.retrieve() //
							.bodyToMono(new EntityModelType<FlexibleEmployee>()) //
							.block();

			logger.info(employeeModel.toString());

			EntityModel<FlexibleEmployee> employeeModelWithTemplates = //
					client.get().uri("http://localhost:8080/rest/employees/0") //
							.accept(MediaTypes.HAL_FORMS_JSON) //
							.retrieve() //
							.bodyToMono(new EntityModelType<FlexibleEmployee>()) //
							.block();

			logger.info(employeeModelWithTemplates.toString());
		};
	}
}
