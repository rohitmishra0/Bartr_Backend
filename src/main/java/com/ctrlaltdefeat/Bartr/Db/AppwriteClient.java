package com.ctrlaltdefeat.Bartr.Db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.appwrite.Client;

@Configuration
public class AppwriteClient {
	@Value("${appwrite.project.id}")
	private String projectId;
	@Value("${appwrite.api.key}")
	private String apiKey;
	
	@Bean
	public Client appwriteClient()
	{
		return new Client()
				.setEndpoint("https://cloud.appwrite.io/")
				.setProject(projectId)
				.setKey(apiKey);
	}
}
