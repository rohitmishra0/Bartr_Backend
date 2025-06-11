package com.ctrlaltdefeat.Bartr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BartrApplication {

	public static void main(String[] args) {
		SpringApplication.run(BartrApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
        // Set HttpComponentsClientHttpRequestFactory to enable PATCH
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        return restTemplate;
    }
}

// return UnsafeRestTemplate.createUnsafeRestTemplate();