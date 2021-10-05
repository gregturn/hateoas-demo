package com.greglturnquist.hateoasdemo.server;

import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication
@EnableHypermediaSupport(type = { HAL, HAL_FORMS })
public class HateoasServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HateoasServerApplication.class, args);
	}

}
