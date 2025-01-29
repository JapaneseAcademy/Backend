package com.academy.backend.service.course;

import com.academy.backend.dto.request.CourseCreateRequest;
import com.academy.backend.dto.response.CourseCreateResponse;

public interface CourseService {
    public CourseCreateResponse createCourse(CourseCreateRequest request);
}
