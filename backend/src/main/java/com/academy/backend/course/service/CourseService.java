package com.academy.backend.course.service;

import com.academy.backend.course.dto.request.CourseCreateRequest;
import com.academy.backend.course.dto.response.CourseDetailResponse;
import com.academy.backend.course.dto.response.CourseListResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {
    void createCourse(CourseCreateRequest request, MultipartFile mainImage, List<MultipartFile> descriptions);
    CourseDetailResponse getCourse(Long courseId);
    CourseListResponse getAllCourses();
    void deleteCourse(Long courseId);
}
