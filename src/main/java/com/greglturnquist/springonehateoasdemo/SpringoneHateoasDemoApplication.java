package com.greglturnquist.springonehateoasdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;

import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType.*;

@SpringBootApplication
@EnableHypermediaSupport(type = { HAL, HAL_FORMS })
public class SpringoneHateoasDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringoneHateoasDemoApplication.class, args);
	}

}
