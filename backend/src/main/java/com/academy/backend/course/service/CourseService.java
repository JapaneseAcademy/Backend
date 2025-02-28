package com.academy.backend.course.service;

import com.academy.backend.course.domain.Course;
import com.academy.backend.course.dto.request.CourseCreateRequest;
import com.academy.backend.course.dto.response.CourseDetailResponse;
import com.academy.backend.course.dto.response.CourseListResponse;
import org.springframework.web.multipart.MultipartFile;

public interface CourseService {
    void createCourse(CourseCreateRequest request, MultipartFile mainImage);
    Course findCourse(Long courseId);
    CourseDetailResponse getCourse(Long courseId);
    CourseListResponse getAllCourses();
    void deleteCourse(Long courseId);
}
