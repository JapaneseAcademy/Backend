package com.academy.backend.controller;

import com.academy.backend.dto.request.CourseCreateRequest;
import com.academy.backend.dto.response.course.CourseCreateResponse;
import com.academy.backend.dto.response.course.CourseResponse;
import com.academy.backend.service.course.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/courses")
@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("")
    public ResponseEntity<CourseCreateResponse> createCourse(@Valid @RequestBody CourseCreateRequest request) {
        CourseCreateResponse response = courseService.createCourse(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponse> getCourse(@PathVariable Long courseId) {
        CourseResponse response = courseService.getCourse(courseId);

        return ResponseEntity.ok(response);
    }
}
