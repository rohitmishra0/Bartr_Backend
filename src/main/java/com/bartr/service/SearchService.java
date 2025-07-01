package com.bartr.service;


import com.bartr.model.Course;

import java.util.List;

public interface SearchService {

    List<Course> search(String keyword);
}
