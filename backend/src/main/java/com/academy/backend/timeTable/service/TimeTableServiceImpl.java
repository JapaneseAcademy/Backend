package com.academy.backend.timeTable.service;

import com.academy.backend.course.domain.Course;
import com.academy.backend.timeBlock.service.TimeBlockService;
import com.academy.backend.timeTable.domain.TimeTable;
import com.academy.backend.timeTable.dto.request.TimeTableRequest;
import com.academy.backend.timeTable.repository.TimeTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeTableServiceImpl implements TimeTableService {

    private final TimeBlockService timeBlockService;

    private final TimeTableRepository timeTableRepository;

    private TimeTable saveTimeTable(Course course) {
        TimeTable timeTable = TimeTable.builder()
                .course(course)
                .build();

        return timeTableRepository.save(timeTable);
    }

    @Override
    @Transactional
    public void createTimeTable(Course course, List<TimeTableRequest> requests) {
        requests.forEach(request -> {
            TimeTable timeTable = TimeTable.builder()
                    .course(course)
                    .build();
            timeTableRepository.save(timeTable);
            timeBlockService.createTimeBlock(timeTable, request.getTimeBlocks());
        });

    }
}
