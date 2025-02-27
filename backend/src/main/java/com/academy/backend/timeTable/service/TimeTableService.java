package com.academy.backend.timeTable.service;

import com.academy.backend.course.domain.Course;
import com.academy.backend.course.dto.request.TimeTableRequest;

import java.util.List;

public interface TimeTableService {

    void createTimeTable(Course course, List<TimeTableRequest> requests);
}
