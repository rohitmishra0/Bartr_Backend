package com.ctrlaltdefeat.Bartr.services;
import com.ctrlaltdefeat.Bartr.models.Enrollment;
import com.ctrlaltdefeat.Bartr.repository.EnrollmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;
import java.util.Map;
@Service
public class EnrollmentService {
   private final EnrollmentRepository enrollmentRepository;
      private final ObjectMapper objectMapper =new ObjectMapper();

   public EnrollmentService(EnrollmentRepository enrollmentRepository) {
       this.enrollmentRepository = enrollmentRepository;
   }
   public String enrollUser(Enrollment enrollment) {
       // Business logic for XP checks, eligibility, etc. can be added here.
       Map<String,Object> data = objectMapper.convertValue(enrollment, Map.class);
       return enrollmentRepository.createDocument(data);
   }
   public String getEnrollmentById(String id) {
       return enrollmentRepository.getDocument(id);
   }
   public String getAllEnrollments() {
       return enrollmentRepository.listDocuments();
   }
   public String deleteEnrollment(String id) {
       return enrollmentRepository.deleteDocument(id);
   }
}