package com.bartr.service.impl;

import com.bartr.model.Course;
import com.bartr.repository.CourseRepository;
import com.bartr.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(int id, Course updatedCourse) {
        Course existing = getCourseById(id);
        existing.setTitle(updatedCourse.getTitle());
        existing.setDescription(updatedCourse.getDescription());
        existing.setCategory(updatedCourse.getCategory());
        existing.setCreator(updatedCourse.getCreator());
        existing.setCreatedAt(updatedCourse.getCreatedAt());
        existing.setLevel(updatedCourse.getLevel());
        return courseRepository.save(existing);
    }

    @Override
    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course getCourseById(int id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getCoursesByCreatorId(int creatorId) {
        return courseRepository.findByCreatorId(creatorId);
    }

    @Override
    public List<Course> getCoursesByCategoryId(int categoryId) {
        return courseRepository.findByCategoryId(categoryId);
    }
}
