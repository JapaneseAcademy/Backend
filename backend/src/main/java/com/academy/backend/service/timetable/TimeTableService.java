package com.academy.backend.service.timetable;

import com.academy.backend.domain.course.Course;
import com.academy.backend.domain.timetable.TimeTable;
import com.academy.backend.dto.request.CourseTimeTableRequest;

import java.util.List;

public interface TimeTableService {
    public List<TimeTable> createTimeTable(Course course, List<CourseTimeTableRequest> requests);

    public List<TimeTable> getTimeTablesByCourse(Course course);
}
