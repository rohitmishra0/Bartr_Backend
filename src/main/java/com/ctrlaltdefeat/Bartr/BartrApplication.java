package com.ctrlaltdefeat.Bartr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BartrApplication {

	public static void main(String[] args) {
		SpringApplication.run(BartrApplication.class, args);
	}

	@Bean
	public RestTemplate RestTemplate(){
		return UnsafeRestTemplate.createUnsafeRestTemplate();
	}

}
