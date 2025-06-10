package com.ctrlaltdefeat.Bartr.repository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
@Repository
public class EnrollmentRepository extends AppwriteRestRepository {
   @Value("${appwrite.collection.enrollments}")
   private String collectionId;
   public EnrollmentRepository(RestTemplate restTemplate) {
       super(restTemplate);
   }
   @Override
   protected String getCollectionId() {
       return collectionId;
   }
}