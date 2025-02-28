package com.academy.backend.timeTable.service;

import com.academy.backend.course.domain.Course;
import com.academy.backend.timeTable.dto.request.TimeTableRequest;
import com.academy.backend.timeTable.dto.response.TimeTableResponse;

import java.util.List;

public interface TimeTableService {

    void createTimeTable(Course course, List<TimeTableRequest> requests);

    List<TimeTableResponse> getTimeTablesByCourseId(Long courseId);
}
