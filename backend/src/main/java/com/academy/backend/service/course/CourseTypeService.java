package com.academy.backend.service.course;

import com.academy.backend.domain.course.Course;
import com.academy.backend.domain.course.CourseType;
import com.academy.backend.dto.request.CourseTypeRequest;

import java.util.List;

public interface CourseTypeService {
    public List<CourseType> createCourseType(Course course, List<CourseTypeRequest> requests);

    public List<CourseType> getCourseTypesByCourse(Course course);
}
