package com.academy.backend.timeTable.service;

import com.academy.backend.course.domain.Course;
import com.academy.backend.timeTable.domain.TimeTable;
import com.academy.backend.course.dto.request.CourseTimeTableRequest;

import java.util.List;

public interface TimeTableService {
    void createTimeTable(Course course, List<CourseTimeTableRequest> requests);
    List<TimeTable> getTimeTablesByCourse(Course course);
}
