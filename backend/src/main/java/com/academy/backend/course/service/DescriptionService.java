package com.academy.backend.course.service;

import com.academy.backend.course.domain.Course;
import com.academy.backend.course.domain.Description;

import java.util.List;

public interface DescriptionService {
    void createDescription(Course course, List<String> imageUrl);
    List<Description> getDescriptionsByCourse(Course course);
}
