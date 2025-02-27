package com.academy.backend.timeBlock.service;

import com.academy.backend.course.domain.Course;
import com.academy.backend.timeBlock.domain.TimeBlock;
import com.academy.backend.course.dto.request.CourseTimeTableRequest;

import java.util.List;

public interface TimeBlockService {
    void createTimeBlock(Course course, List<CourseTimeTableRequest> requests);
    List<TimeBlock> getTimeBlocksByCourse(Course course);
}
