package com.batr.service;

import java.util.List;

import com.batr.model.Course;

public interface CourseService {
    Course createCourse(Course course);

    Course updateCourse(int id, Course course);

    void deleteCourse(int id);

    Course getCourseById(int id);

    List<Course> getAllCourses();

    List<Course> getCoursesByCreatorId(int creatorId);

    List<Course> getCoursesByCategoryId(int categoryId);
}
