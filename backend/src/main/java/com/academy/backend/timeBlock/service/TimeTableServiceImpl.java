package com.academy.backend.timeBlock.service;

import com.academy.backend.course.domain.Course;
import com.academy.backend.timeBlock.domain.TimeBlock;
import com.academy.backend.course.dto.request.CourseTimeTableRequest;
import com.academy.backend.timeBlock.repository.TimeTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeTableServiceImpl implements TimeTableService {

    private final TimeTableRepository timeTableRepository;

    @Override
    @Transactional
    public void createTimeTable(Course course, List<CourseTimeTableRequest> requests) {
        requests.forEach(schedule -> {
            TimeBlock timeBlock = TimeBlock.builder()
                    .course(course)
                    .weekday(schedule.getWeekday())
                    .startTime(schedule.getStartTime())
                    .endTime(schedule.getEndTime())
                    .build();
            timeTableRepository.save(timeBlock);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public List<TimeBlock> getTimeTablesByCourse(Course course) {
        return timeTableRepository.findByCourseId(course.getId());
    }
}
