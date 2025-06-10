package com.ctrlaltdefeat.Bartr.repository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.ctrlaltdefeat.Bartr.models.Course;
@Repository
public class CourseRepository extends AppwriteRestRepository<Course> {
   @Value("${appwrite.collection.courses}")
   private String collectionId;
   public CourseRepository(RestTemplate restTemplate) {
       super(restTemplate);
   }
   @Override
   protected String getCollectionId() {
       return collectionId;
   }

   @Override
   protected Class<Course> getEntityClass() {
       return Course.class;
   }
}