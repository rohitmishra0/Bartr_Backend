package com.batr.service.impl;

import com.batr.model.Category;
import com.batr.model.Course;
import com.batr.model.User;

import com.batr.repository.CategoryRepository;
import com.batr.repository.CourseRepository;
import com.batr.repository.UserRepository;
import com.batr.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    @Override
    public Course createCourse(Course course){
        Category category = categoryRepository.findById(course.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        User creator = userRepository.findById(course.getCreator().getId())
                        .orElseThrow(() -> new RuntimeException("Creator not found"));
        course.setCategory(category);
        course.setCreator(creator);
        double multiplier = course.getLevel().getMultiplier();

    int finalPrice = (int) Math.round(category.getXpCost() * multiplier);
    System.out.println("Calculated price XP: "+ finalPrice);
    course.setCoursePrice(finalPrice);

    course.setCreatedAt(new Date());

    return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(int id, Course course) {
        Course existingCourse = courseRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Course not found with ID: "+id));

        Category category = categoryRepository.findById(course.getCategory().getId())
                                .orElseThrow(() -> new RuntimeException("Category not found with Id: "+course.getCategory()));
                
        User creator = userRepository.findById(course.getCreator().getId())
                                        .orElseThrow(() -> new RuntimeException("Creator not found with Id: "+course.getCreator().getId()));
        
        existingCourse.setTitle(course.getTitle());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setLevel(course.getLevel());
        existingCourse.setCoursePrice(course.getCoursePrice());
        existingCourse.setCategory(category);
        existingCourse.setCreator(creator);

        return courseRepository.save(existingCourse);
    }

    @Override
    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    
    }

    @Override
    public Course getCourseById(int id){
        return courseRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Course not found with ID: "+id));
    }

    @Override
    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getCoursesByCreatorId(int creatorId){
        return courseRepository.findByCreatorId(creatorId);
    }

    @Override
    List<Course> getCoursesByCategoryId(int categoryId){
        return courseRepository.findByCategoryId(categoryId);
    }
}
