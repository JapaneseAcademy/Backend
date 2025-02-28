package com.academy.backend.course.service;

import com.academy.backend.course.domain.Course;
import com.academy.backend.course.domain.Description;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DescriptionService {
    void createDescription(Course course, List<MultipartFile> imageDescriptions);
    List<Description> getDescriptionsByCourse(Course course);
}
