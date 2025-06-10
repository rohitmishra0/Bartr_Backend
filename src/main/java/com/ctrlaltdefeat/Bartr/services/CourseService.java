package com.ctrlaltdefeat.Bartr.services;
import com.ctrlaltdefeat.Bartr.models.Course;
import com.ctrlaltdefeat.Bartr.repository.CourseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class CourseService {
   private final CourseRepository courseRepository;
   private final ObjectMapper objectMapper =new ObjectMapper();
   public CourseService(CourseRepository courseRepository) {
       this.courseRepository = courseRepository;
   }
   public Course createCourse(Course course) {
        Map<String,Object> data = objectMapper.convertValue(course, Map.class);
       return courseRepository.createDocument(data);
   }
   public Course getCourseById(String id) {
       return courseRepository.getDocument(id);
   }
   public List<Course> getAllCourses() {
       return courseRepository.listDocuments();
   }
   public Course updateCourse(String id, Course course) {
    Map<String,Object> data = objectMapper.convertValue(course, Map.class);
       return courseRepository.updateDocument(id, data);
   }
   public void deleteCourse(String id) {
        courseRepository.deleteDocument(id);
   }
}