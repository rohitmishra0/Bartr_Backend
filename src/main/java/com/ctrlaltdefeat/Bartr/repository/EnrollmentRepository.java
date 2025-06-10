package com.ctrlaltdefeat.Bartr.repository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.ctrlaltdefeat.Bartr.models.Enrollment;
@Repository
public class EnrollmentRepository extends AppwriteRestRepository<Enrollment> {
   @Value("${appwrite.collection.enrollments}")
   private String collectionId;
   public EnrollmentRepository(RestTemplate restTemplate) {
       super(restTemplate);
   }
   @Override
   protected String getCollectionId() {
       return collectionId;
   }

   @Override
   protected Class<Enrollment> getEntityClass() {
       return Enrollment.class;
   }
}