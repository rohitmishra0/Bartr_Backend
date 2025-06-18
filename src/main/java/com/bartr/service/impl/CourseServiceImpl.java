package com.bartr.service.impl;

import com.bartr.dao.CourseDao;
import com.bartr.model.Course;
import com.bartr.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;

    @Override
    public Course createCourse(Course course) {
        return courseDao.save(course);
    }

    @Override
    public Course updateCourse(int id, Course course) {
        Course existingCourse = courseDao.findById(id).orElse(null);
        if (existingCourse == null) return null;

        // Update fields (you can customize which fields are updatable)
        existingCourse.setTitle(course.getTitle());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setCategory(course.getCategory());
        existingCourse.setCreator(course.getCreator());

        return courseDao.save(existingCourse);
    }

    @Override
    public void deleteCourse(int id) {
        courseDao.deleteById(id);
    }

    @Override
    public Course getCourseById(int id) {
        return courseDao.findById(id).orElse(null);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDao.findAll();
    }

    @Override
    public List<Course> getCoursesByCreatorId(int creatorId) {
        return courseDao.findByCreatorId(creatorId);
    }

    @Override
    public List<Course> getCoursesByCategoryId(int categoryId) {
        return courseDao.findByCategoryId(categoryId);
    }
}
