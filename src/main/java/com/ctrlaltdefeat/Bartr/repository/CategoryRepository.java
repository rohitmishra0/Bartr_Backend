package com.ctrlaltdefeat.Bartr.repository;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Value;
=======
 
>>>>>>> 5889fd7 (updated application.properties)
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
@Repository
<<<<<<< HEAD
public class CategoryRepository extends AppwriteRestRepository {
   @Value("${appwrite.collection.categories}")
   private String collectionId;
   public CategoryRepository(RestTemplate restTemplate) {
       super(restTemplate);
   }
   @Override
   protected String getCollectionId() {
       return collectionId;
   }
=======
public class CategoryRepository {
   
>>>>>>> 5889fd7 (updated application.properties)
}