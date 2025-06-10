package com.ctrlaltdefeat.Bartr.repository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
public abstract class AppwriteRestRepository {
   protected final RestTemplate restTemplate;
   @Value("${appwrite.endpoint}")
   protected String baseUrl;
   @Value("${appwrite.project.id}")
   protected String projectId;
   @Value("${appwrite.api.key}")
   protected String apiKey;
   @Value("${appwrite.database.id}")
   protected String databaseId;
   public AppwriteRestRepository(RestTemplate restTemplate) {
       this.restTemplate = restTemplate;
   }
   protected abstract String getCollectionId();
   public String createDocument(Map<String, Object> data) {
       HttpHeaders headers = buildHeaders();
       Map<String, Object> body = Map.of("documentId", "unique()", "data", data);
       HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
       ResponseEntity<String> response = restTemplate.postForEntity(
               baseUrl + "/databases/" + databaseId + "/collections/" + getCollectionId() + "/documents",
               request,
               String.class
       );
       return response.getBody();
   }
   public String getDocument(String id) {
       HttpHeaders headers = buildHeaders();
       HttpEntity<Void> request = new HttpEntity<>(headers);
       ResponseEntity<String> response = restTemplate.exchange(
               baseUrl + "/databases/" + databaseId + "/collections/" + getCollectionId() + "/documents/" + id,
               HttpMethod.GET,
               request,
               String.class
       );
       return response.getBody();
   }
   public String listDocuments() {
       HttpHeaders headers = buildHeaders();
       HttpEntity<Void> request = new HttpEntity<>(headers);
       ResponseEntity<String> response = restTemplate.exchange(
               baseUrl + "/databases/" + databaseId + "/collections/" + getCollectionId() + "/documents",
               HttpMethod.GET,
               request,
               String.class
       );
       return response.getBody();
   }
   public String updateDocument(String id, Map<String, Object> data) {
       HttpHeaders headers = buildHeaders();
       Map<String, Object> body = Map.of("data", data);
       HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
       ResponseEntity<String> response = restTemplate.exchange(
               baseUrl + "/databases/" + databaseId + "/collections/" + getCollectionId() + "/documents/" + id,
               HttpMethod.PATCH,
               request,
               String.class
       );
       return response.getBody();
   }
   public String deleteDocument(String id) {
       HttpHeaders headers = buildHeaders();
       HttpEntity<Void> request = new HttpEntity<>(headers);
       ResponseEntity<String> response = restTemplate.exchange(
               baseUrl + "/databases/" + databaseId + "/collections/" + getCollectionId() + "/documents/" + id,
               HttpMethod.DELETE,
               request,
               String.class
       );
       return response.getBody();
   }
   private HttpHeaders buildHeaders() {
       HttpHeaders headers = new HttpHeaders();
       headers.set("X-Appwrite-Project", projectId);
       headers.set("X-Appwrite-Key", apiKey);
       headers.setContentType(MediaType.APPLICATION_JSON);
       return headers;
   }
}