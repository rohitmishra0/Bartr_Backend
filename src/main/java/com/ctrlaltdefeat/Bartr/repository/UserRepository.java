package com.ctrlaltdefeat.Bartr.repository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.ctrlaltdefeat.Bartr.models.User;
@Repository
public class UserRepository extends AppwriteRestRepository<User> {
   @Value("${appwrite.collection.users}")
   private String collectionId;
   public UserRepository(RestTemplate restTemplate) {
       super(restTemplate);
   }
   @Override
   protected String getCollectionId() {
       return collectionId;
   }

   @Override
   protected Class<User> getEntityClass() {
       return User.class;
   }
}