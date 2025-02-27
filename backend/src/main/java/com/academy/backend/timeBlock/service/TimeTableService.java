package com.academy.backend.timeBlock.service;

import com.academy.backend.course.domain.Course;
import com.academy.backend.timeBlock.domain.TimeBlock;
import com.academy.backend.course.dto.request.CourseTimeTableRequest;

import java.util.List;

public interface TimeTableService {
    void createTimeTable(Course course, List<CourseTimeTableRequest> requests);
    List<TimeBlock> getTimeTablesByCourse(Course course);
}
