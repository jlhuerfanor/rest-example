package com.endava.workshops.restexample.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan({
	"com.endava.workshops.restexample.application",
	"com.endava.workshops.restexample.infrastructure"})
@PropertySource("classpath:application.yaml")
public class RestExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestExampleApplication.class, args);
	}

}
