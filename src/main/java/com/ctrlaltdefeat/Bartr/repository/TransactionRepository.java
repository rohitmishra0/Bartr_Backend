package com.ctrlaltdefeat.Bartr.repository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
@Repository
public class TransactionRepository extends AppwriteRestRepository {
   @Value("${appwrite.collection.transactions}")
   private String collectionId;
   public TransactionRepository(RestTemplate restTemplate) {
       super(restTemplate);
   }
   @Override
   protected String getCollectionId() {
       return collectionId;
   }
}