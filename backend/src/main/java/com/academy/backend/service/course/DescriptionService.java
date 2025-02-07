package com.academy.backend.service.course;

import com.academy.backend.domain.course.Course;
import com.academy.backend.domain.course.Description;

import java.util.List;

public interface DescriptionService {
    void createDescription(Course course, List<String> imageUrl);
    List<Description> getDescriptionsByCourse(Course course);
}
