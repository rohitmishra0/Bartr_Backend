package com.bartr.service.impl;

import com.bartr.model.Course;
import com.bartr.repository.CourseRepository;
import com.bartr.service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final CourseRepository courseRepository;

    @Override
    public List<Course> search(String keyword, int userId){
        return courseRepository.searchRelevantCourses(keyword,userId);
    }
}
