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
<<<<<<< HEAD
	public RestTemplate RestTemplate(){
=======
	public RestTemplate restTemplate(){
>>>>>>> 5889fd7 (updated application.properties)
		return new RestTemplate();
	}

}
