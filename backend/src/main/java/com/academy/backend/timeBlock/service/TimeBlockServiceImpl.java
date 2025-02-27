package com.academy.backend.timeBlock.service;

import com.academy.backend.course.domain.Course;
import com.academy.backend.course.dto.request.TimeTableRequest.TimeBlockRequest;
import com.academy.backend.timeBlock.domain.TimeBlock;
import com.academy.backend.timeBlock.repository.TimeBlockRepository;
import com.academy.backend.timeTable.domain.TimeTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeBlockServiceImpl implements TimeBlockService {

    private final TimeBlockRepository timeBlockRepository;

    @Override
    @Transactional
    public void createTimeBlock(TimeTable timeTable, List<TimeBlockRequest> requests) {
        requests.forEach(request -> {
            TimeBlock timeBlock = TimeBlock.builder()
                    .timeTable(timeTable)
                    .weekday(request.getWeekday())
                    .startTime(request.getStartTime())
                    .endTime(request.getEndTime())
                    .build();
            timeBlockRepository.save(timeBlock);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public List<TimeBlock> getTimeBlocksByCourse(Course course) {
        return timeBlockRepository.findByTimeTableId(course.getId());
    }
}
