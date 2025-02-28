package com.academy.backend.course.service;

import com.academy.backend.course.domain.Course;
import com.academy.backend.course.dto.request.CourseCreateRequest;
import com.academy.backend.course.dto.response.CourseDetailResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {
    void createCourse(CourseCreateRequest request, MultipartFile mainImage);
    Course findCourse(Long courseId);
    CourseDetailResponse getCourse(Long courseId);
    List<CourseDetailResponse> getAllCourses();
    void deleteCourse(Long courseId);
}
