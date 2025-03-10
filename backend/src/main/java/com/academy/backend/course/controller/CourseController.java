package com.academy.backend.course.controller;

import com.academy.backend.course.dto.request.CourseCreateRequest;
import com.academy.backend.course.dto.response.CourseDetailResponse;
import com.academy.backend.course.dto.response.CourseListResponse;
import com.academy.backend.course.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api/v1/courses")
@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("")
    public ResponseEntity<CourseListResponse> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PostMapping("")
    public ResponseEntity<?> createCourse(
            @Valid @RequestPart("request") CourseCreateRequest request,
            @RequestPart("mainImage") MultipartFile mainImage,
            @RequestPart("descriptions") List<MultipartFile> descriptions
    ) {
        courseService.createCourse(request, mainImage, descriptions);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDetailResponse> getCourse(@PathVariable Long courseId) {
        CourseDetailResponse response = courseService.getCourse(courseId);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
