package com.academy.backend.timeBlock.service;

import com.academy.backend.course.domain.Course;
import com.academy.backend.timeBlock.domain.TimeBlock;
import com.academy.backend.course.dto.request.CourseTimeTableRequest;
import com.academy.backend.timeBlock.repository.TimeBlockRepository;
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
    public void createTimeBlock(Course course, List<CourseTimeTableRequest> requests) {
        requests.forEach(schedule -> {
            TimeBlock timeBlock = TimeBlock.builder()
                    .course(course)
                    .weekday(schedule.getWeekday())
                    .startTime(schedule.getStartTime())
                    .endTime(schedule.getEndTime())
                    .build();
            timeBlockRepository.save(timeBlock);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public List<TimeBlock> getTimeBlocksByCourse(Course course) {
        return timeBlockRepository.findByCourseId(course.getId());
    }
}
