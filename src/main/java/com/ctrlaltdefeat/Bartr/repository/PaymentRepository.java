package com.ctrlaltdefeat.Bartr.repository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
@Repository
public class PaymentRepository extends AppwriteRestRepository {
   @Value("${appwrite.collection.payments}")
   private String collectionId;
   public PaymentRepository(RestTemplate restTemplate) {
       super(restTemplate);
   }
   @Override
   protected String getCollectionId() {
       return collectionId;
   }
}