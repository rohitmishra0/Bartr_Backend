package com.ctrlaltdefeat.Bartr.services;
import com.ctrlaltdefeat.Bartr.models.Category;
import com.ctrlaltdefeat.Bartr.models.Course;
import com.ctrlaltdefeat.Bartr.models.Enrollment;
import com.ctrlaltdefeat.Bartr.models.User;
import com.ctrlaltdefeat.Bartr.repository.EnrollmentRepository;
import com.ctrlaltdefeat.Bartr.repository.UserRepository;
import com.ctrlaltdefeat.Bartr.repository.CategoryRepository;
import com.ctrlaltdefeat.Bartr.repository.CourseRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class EnrollmentService {
   private final EnrollmentRepository enrollmentRepository;
   private final UserRepository userRepository;
   private final CourseRepository courseRepository;
   private final CategoryRepository categoryRepository;

      private final ObjectMapper objectMapper =new ObjectMapper();

   public EnrollmentService(EnrollmentRepository enrollmentRepository, UserRepository userRepository,CourseRepository courseRepository,CategoryRepository categoryRepository) {
       this.enrollmentRepository = enrollmentRepository;
       this.userRepository = userRepository;
       this.courseRepository = courseRepository;
       this.categoryRepository = categoryRepository;
   }
   public Enrollment enrollUser(String userId,String courseId) {
       // Business logic for XP checks, eligibility, etc. can be added here. 
        User user = userRepository.getDocument(userId);
        Course course = courseRepository.getDocument(courseId);

    int userXp = user.getXp();
    String categoryId = course.getCategory_id(); // or course.getCategory_id()
    Category category = categoryRepository.getDocument(categoryId);
    int xpCost = category.getXp_cost();

    if (userXp < xpCost) {
        throw new RuntimeException("Insufficient XP to enroll in this course.");
    }

    // 2. Deduct XP from user
    user.setXp(userXp - xpCost);
    Map<String, Object> updatedUser = objectMapper.convertValue(user, Map.class);
    userRepository.updateDocument(userId, updatedUser);

    // 3. Credit XP to creator (instructor)
    String creatorId = course.getCreator_id();
    User creator = userRepository.getDocument(creatorId);
    creator.setXp(creator.getXp() + xpCost);
    Map<String, Object> updatedCreator = objectMapper.convertValue(creator, Map.class);
    userRepository.updateDocument(creatorId, updatedCreator);

    // 4. Create enrollment
    Map<String, Object> enrollmentData = new HashMap<>();
    enrollmentData.put("userId", userId);
    enrollmentData.put("courseId", courseId);
       return enrollmentRepository.createDocument(enrollmentData);
   }



   public Enrollment getEnrollmentById(String id) {
       return enrollmentRepository.getDocument(id);
   }
   public List<Enrollment> getAllEnrollments() {
       return enrollmentRepository.listDocuments();
   }
   public void deleteEnrollment(String id) {
        enrollmentRepository.deleteDocument(id);
   }


   public List<Enrollment> getEnrollmentsForUser(String userId) {
    
    return getAllEnrollments().stream()
            .filter(e -> userId.equals(e.getId()))
            .collect(Collectors.toList());
    }

    public List<Enrollment> getEnrollmentsForCourse(String courseId) {
        return getAllEnrollments().stream()
                .filter(e -> courseId.equals(e.getId()))
                .collect(Collectors.toList());
    }
}