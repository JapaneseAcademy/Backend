package com.academy.backend.service.timetable;

import com.academy.backend.domain.course.Course;
import com.academy.backend.domain.timetable.TimeTable;
import com.academy.backend.dto.request.course.CourseTimeTableRequest;

import java.util.List;

public interface TimeTableService {
    void createTimeTable(Course course, List<CourseTimeTableRequest> requests);
    List<TimeTable> getTimeTablesByCourse(Course course);
}
