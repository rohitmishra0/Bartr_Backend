package com.ctrlaltdefeat.Bartr.services;
import com.ctrlaltdefeat.Bartr.models.Course;
import com.ctrlaltdefeat.Bartr.repository.CourseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;
import java.util.Map;
@Service
public class CourseService {
   private final CourseRepository courseRepository;
   private final ObjectMapper objectMapper =new ObjectMapper();
   public CourseService(CourseRepository courseRepository) {
       this.courseRepository = courseRepository;
   }
   public String createCourse(Course course) {
        Map<String,Object> data = objectMapper.convertValue(course, Map.class);
       return courseRepository.createDocument(data);
   }
   public String getCourseById(String id) {
       return courseRepository.getDocument(id);
   }
   public String getAllCourses() {
       return courseRepository.listDocuments();
   }
   public String updateCourse(String id, Course course) {
    Map<String,Object> data = objectMapper.convertValue(course, Map.class);
       return courseRepository.updateDocument(id, data);
   }
   public String deleteCourse(String id) {
       return courseRepository.deleteDocument(id);
   }
}