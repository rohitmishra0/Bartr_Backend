package com.bartr.service.impl;

import com.bartr.dao.CourseDao;
import com.bartr.model.Category;
import com.bartr.model.Course;
import com.bartr.model.User;
import com.bartr.repository.CategoryRepository;
import com.bartr.repository.EnrollmentRepository;
import com.bartr.repository.UserRepository;
import com.bartr.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public Course createCourse(Course course) {
        Category category = categoryRepository.findById(course.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Fetch and validate creator
        User creator = userRepository.findById(course.getCreator().getId())
                .orElseThrow(() -> new RuntimeException("Creator not found"));

        // Set references properly
        course.setCategory(category);
        course.setCreator(creator);

        // Calculate XP-based price
        double multiplier = getLevelMultiplier(course.getLevel());
        double price = category.getXpCost() * multiplier;
        course.setPrice(price);

        return courseDao.save(course);
    }

    @Override
    public Course updateCourse(int id, Course updatedCourse) {


        Course existing = courseDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        existing.setTitle(updatedCourse.getTitle());
        existing.setDescription(updatedCourse.getDescription());

        if (updatedCourse.getCategory() != null) {
            Category category = categoryRepository.findById(updatedCourse.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existing.setCategory(category);
        }

        if (updatedCourse.getCreator() != null) {
            User creator = userRepository.findById(updatedCourse.getCreator().getId())
                    .orElseThrow(() -> new RuntimeException("Creator not found"));
            existing.setCreator(creator);
        }

        existing.setLevel(updatedCourse.getLevel());

        // Recalculate price
        double price = existing.getCategory().getXpCost() * getLevelMultiplier(existing.getLevel());
        existing.setPrice(price);

       
        return courseDao.save(existing);
    }

    @Override
    public void deleteCourse(int id) {
        courseDao.deleteById(id);
    }

    @Override
    public Course getCourseById(int id) {
        return courseDao.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public List<Course> getAllCourses() {

        List<Course> courses= courseDao.findAll();
        for(Course course: courses){
            course.setEnrolledUser(course.getEnrollments().size());

        }
        return courses;
    }

    @Override
    public List<Course> getCoursesByCreatorId(int creatorId) {
        User creator = userRepository.findById(creatorId).orElseThrow(() -> new RuntimeException("Creator not found"));;
        return courseDao.findByCreatorId(creatorId);
    }

    @Override
    public List<Course> getCoursesByCategoryId(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Categoty not found"));;
        return courseDao.findByCategoryId(categoryId);
    }

    private double getLevelMultiplier(String level) {
        switch (level.toLowerCase()) {
            case "beginner":
                return 1.0;
            case "intermediate":
                return 1.25;
            case "advanced":
                return 1.5;
            default:
                throw new IllegalArgumentException("Invalid level: " + level);
        }
    }

}
