package com.bartr.service.impl;

import com.bartr.exception.UserNameNotFoundException;
import com.bartr.model.Course;
import com.bartr.model.User;
import com.bartr.repository.CourseRepository;
import com.bartr.repository.UserRepository;
import com.bartr.service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    public List<Course> search(String keyword, int userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNameNotFoundException("User not found"));
        return courseRepository.searchRelevantCourses(keyword,userId);
    }
}
