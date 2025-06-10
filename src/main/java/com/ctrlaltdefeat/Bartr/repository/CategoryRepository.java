package com.ctrlaltdefeat.Bartr.repository;
import com.ctrlaltdefeat.Bartr.models.Category;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
@Repository
public class CategoryRepository extends AppwriteRestRepository<Category> {
   @Value("${appwrite.collection.categories}")
   private String collectionId;
   public CategoryRepository(RestTemplate restTemplate) {
       super(restTemplate);
   }
   @Override
   protected String getCollectionId() {
       return collectionId;
   }
   @Override
   protected Class<Category> getEntityClass() {
       return Category.class;
   }
}