package com.greglturnquist.springonehateaoasdemo.resttemplate;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.HypermediaRestTemplateConfigurer;
import org.springframework.hateoas.server.core.TypeReferences;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType.*;

@SpringBootApplication
@EnableHypermediaSupport(type = { HAL, HAL_FORMS })
public class SpringOneRestTemplateApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringOneRestTemplateApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringOneRestTemplateApplication.class, args);
	}

	@Bean
	RestTemplateCustomizer hypermedia(HypermediaRestTemplateConfigurer configurer) {
		return template -> configurer.registerHypermediaTypes(template);
	}

	@Bean
	CommandLineRunner talktoServer(RestTemplateBuilder builder) {
		return args -> {
			RestTemplate template = builder.build();

			ResponseEntity<EntityModel<FlexibleEmployee>> employeeModel = //
					template.exchange( //
							"http://localhost:8080/rest/employees/0", //
							HttpMethod.GET, //
							null, //
							new TypeReferences.EntityModelType<>() {});

			logger.info(employeeModel.toString());

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaTypes.HAL_FORMS_JSON));
			HttpEntity<String> entity = new HttpEntity<>("body", headers);

			ResponseEntity<EntityModel<FlexibleEmployee>> employeeModelWithTemplates = template.exchange(
					"http://localhost:8080/rest/employees/0", HttpMethod.GET, entity, new TypeReferences.EntityModelType<>() {});

			logger.info(employeeModelWithTemplates.toString());
		};
	}
}
