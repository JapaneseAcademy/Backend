package com.academy.backend.service.course;

import com.academy.backend.domain.course.Course;
import com.academy.backend.dto.request.course.CourseCreateRequest;
import com.academy.backend.dto.response.course.CourseCreateResponse;
import com.academy.backend.dto.response.course.CourseResponse;

import java.util.List;

public interface CourseService {
    CourseCreateResponse createCourse(CourseCreateRequest request);
    Course findCourse(Long courseId);
    CourseResponse getCourse(Long courseId);
    List<CourseResponse> getAllCourses();
    void deleteCourse(Long courseId);
}
