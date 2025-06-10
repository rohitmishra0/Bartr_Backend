package com.ctrlaltdefeat.Bartr.repository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
@Repository
public class CourseRepository extends AppwriteRestRepository {
   @Value("${appwrite.collection.courses}")
   private String collectionId;
   public CourseRepository(RestTemplate restTemplate) {
       super(restTemplate);
   }
   @Override
   protected String getCollectionId() {
       return collectionId;
   }
}