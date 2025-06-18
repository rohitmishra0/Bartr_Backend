package com.bartr.dao;

import com.bartr.model.Course;
import com.bartr.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CourseDao {

    private final CourseRepository courseRepository;

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public Optional<Course> findById(int id) {
        return courseRepository.findById(id);
    }

    public void deleteById(int id) {
        courseRepository.deleteById(id);
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public List<Course> findByCreatorId(int creatorId) {
        return courseRepository.findByCreatorId(creatorId);
    }

    public List<Course> findByCategoryId(int categoryId) {
        return courseRepository.findByCategoryId(categoryId);
    }
}
