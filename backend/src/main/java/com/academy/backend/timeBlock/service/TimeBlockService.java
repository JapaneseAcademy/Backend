package com.academy.backend.timeBlock.service;

import com.academy.backend.course.domain.Course;
import com.academy.backend.timeBlock.domain.TimeBlock;
import com.academy.backend.timeTable.domain.TimeTable;
import com.academy.backend.timeTable.dto.request.TimeTableRequest.TimeBlockRequest;

import java.util.List;

public interface TimeBlockService {
    void createTimeBlock(TimeTable timeTable, List<TimeBlockRequest> requests);
    List<TimeBlock> getTimeBlocksByCourse(Course course);
}
