package com.ctrlaltdefeat.Bartr.repository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.ctrlaltdefeat.Bartr.models.Transaction;
@Repository
public class TransactionRepository extends AppwriteRestRepository<Transaction> {
   @Value("${appwrite.collection.transactions}")
   private String collectionId;
   public TransactionRepository(RestTemplate restTemplate) {
       super(restTemplate);
   }
   @Override
   protected String getCollectionId() {
       return collectionId;
   }

   @Override
   protected Class<Transaction> getEntityClass() {
       return Transaction.class;
   }
}