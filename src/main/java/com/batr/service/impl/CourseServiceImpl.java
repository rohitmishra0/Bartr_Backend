package com.batr.service.impl;

import com.batr.model.Category;
import com.batr.model.Course;

import com.batr.repository.CategoryRepository;
import com.batr.repository.CourseRepository;
import com.batr.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Course createCourse(Course course){
        Category category = categoryRepository.findBy(course.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));


    double multiplier = course.getLevel().getMultiplier();

    int finalPrice = (int) Math.round(category.getXpCost() * multiplier);
    System.out.println("Calculated price XP: "+ finalPrice);
    course.setCoursePrice(finalPrice);

    course.setCreatedAt(new Date());

    return courseRepository.save(course);
    }
}
