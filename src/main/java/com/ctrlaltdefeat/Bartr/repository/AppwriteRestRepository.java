package com.ctrlaltdefeat.Bartr.repository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public abstract class AppwriteRestRepository<T> {
   protected final RestTemplate restTemplate;
   protected final ObjectMapper objectMapper = new ObjectMapper();
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
   protected abstract Class<T> getEntityClass();
   public T createDocument(Map<String, Object> data) {
       HttpHeaders headers = buildHeaders();
       Map<String, Object> body = Map.of("documentId", "unique()", "data", data);
       HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
       ResponseEntity<Map> response = restTemplate.postForEntity(
               baseUrl + "/databases/" + databaseId + "/collections/" + getCollectionId() + "/documents",
               request,
               Map.class
       );
       Map<String, Object> documentData = response.getBody();
       System.out.println(documentData);
       documentData.keySet().removeIf(key -> key.startsWith("$"));
       return objectMapper.convertValue(documentData, getEntityClass());
   }
   public T getDocument(String id) {
       HttpHeaders headers = buildHeaders();
       HttpEntity<Void> request = new HttpEntity<>(headers);
       ResponseEntity<Map> response = restTemplate.exchange(
               baseUrl + "/databases/" + databaseId + "/collections/" + getCollectionId() + "/documents/" + id,
               HttpMethod.GET,
               request,
               Map.class
       );
       Map<String, Object> documentData = response.getBody();
       System.out.println(documentData);
       documentData.keySet().removeIf(key -> key.startsWith("$"));
       return objectMapper.convertValue(documentData, getEntityClass());
   }
   public List<T> listDocuments() {
       HttpHeaders headers = buildHeaders();
       HttpEntity<Void> request = new HttpEntity<>(headers);
       ResponseEntity<Map> response = restTemplate.exchange(
               baseUrl + "/databases/" + databaseId + "/collections/" + getCollectionId() + "/documents",
               HttpMethod.GET,
               request,
               Map.class
       );
       List<Map<String, Object>> documents = (List<Map<String, Object>>) response.getBody().get("documents");
       System.out.println(documents);

       return documents.stream()
               .map(doc -> {
                doc.keySet().removeIf(key -> key.startsWith("$"));
                return objectMapper.convertValue(doc, getEntityClass());
                })
               .collect(Collectors.toList());
   }
   public T updateDocument(String id, Map<String, Object> data) {
    try {
        HttpHeaders headers = buildHeaders();
        Map<String, Object> body = Map.of("data", data);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        
        ResponseEntity<Map> response = restTemplate.exchange(
            baseUrl + "/databases/" + databaseId + "/collections/" + getCollectionId() + "/documents/" + id,
            HttpMethod.PATCH,
            request,
            Map.class
        );
        
        // Handle success
        Map<String, Object> documentData = response.getBody();
        System.out.println(documentData);
        documentData.keySet().removeIf(key -> key.startsWith("$"));
        return objectMapper.convertValue(documentData, getEntityClass());
    } catch (HttpClientErrorException e) {
        // Client-side error (4xx)
        System.err.println("Client error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        throw e;
    } catch (HttpServerErrorException e) {
        // Server-side error (5xx)
        System.err.println("Server error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        throw e;
    } catch (Exception e) {
        // Other errors
        System.err.println("Unknown error: " + e.getMessage());
        throw e;
    }
}
   public void deleteDocument(String id) {
       HttpHeaders headers = buildHeaders();
       HttpEntity<Void> request = new HttpEntity<>(headers);
       restTemplate.exchange(
               baseUrl + "/databases/" + databaseId + "/collections/" + getCollectionId() + "/documents/" + id,
               HttpMethod.DELETE,
               request,
               Void.class
       );
   }
   private HttpHeaders buildHeaders() {
       HttpHeaders headers = new HttpHeaders();
       headers.set("X-Appwrite-Project", projectId);
       headers.set("X-Appwrite-Key", apiKey);
       headers.setContentType(MediaType.APPLICATION_JSON);
       return headers;
   }
}