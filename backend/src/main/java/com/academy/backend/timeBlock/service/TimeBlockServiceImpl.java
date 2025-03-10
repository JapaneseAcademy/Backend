package com.academy.backend.timeBlock.service;

import com.academy.backend.timeBlock.domain.TimeBlock;
import com.academy.backend.timeBlock.repository.TimeBlockRepository;
import com.academy.backend.timeTable.domain.TimeTable;
import com.academy.backend.timeTable.dto.request.TimeTableRequest.TimeBlockRequest;
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
    public List<TimeBlock> getTimeBlocksByTimeTableId(Long timeTableId) {
        return timeBlockRepository.findByTimeTableId(timeTableId);
    }
}
