package com.academy.backend.service.course;

import com.academy.backend.domain.course.Course;
import com.academy.backend.domain.member.Member;
import com.academy.backend.dto.request.CourseCreateRequest;
import com.academy.backend.dto.response.CourseCreateResponse;

public interface CourseService {
    public Course saveCourse(Member member, CourseCreateRequest request);
    public CourseCreateResponse createCourse(CourseCreateRequest request);
}
