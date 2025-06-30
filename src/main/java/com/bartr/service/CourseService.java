package com.bartr.service;

import java.util.List;

import com.bartr.model.Course;

public interface CourseService {
    Course createCourse(Course course);

    Course updateCourse(int id, Course course);



    Course getCourseById(int id);

    List<Course> getAllCourses();

    List<Course> getCoursesByCreatorId(int creatorId);

    List<Course> getCoursesByCategoryId(int categoryId);

    void deleteCourse(int courseId);

}
