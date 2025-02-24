package com.academy.backend.course.service;

import com.academy.backend.course.domain.Course;
import com.academy.backend.course.dto.request.CourseCreateRequest;
import com.academy.backend.course.dto.response.CourseCreateResponse;
import com.academy.backend.course.dto.response.CourseResponse;

import java.util.List;

public interface CourseService {
    CourseCreateResponse createCourse(CourseCreateRequest request);
    Course findCourse(Long courseId);
    CourseResponse getCourse(Long courseId);
    List<CourseResponse> getAllCourses();
    void deleteCourse(Long courseId);
}
