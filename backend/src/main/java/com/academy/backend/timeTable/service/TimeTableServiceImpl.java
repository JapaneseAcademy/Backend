package com.academy.backend.timeTable.service;

import com.academy.backend.course.domain.Course;
import com.academy.backend.timeTable.domain.TimeTable;
import com.academy.backend.course.dto.request.CourseTimeTableRequest;
import com.academy.backend.timeTable.repository.TimeTableRepository;
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
            TimeTable timeTable = TimeTable.builder()
                    .course(course)
                    .weekday(schedule.getWeekday())
                    .startTime(schedule.getStartTime())
                    .endTime(schedule.getEndTime())
                    .build();
            timeTableRepository.save(timeTable);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public List<TimeTable> getTimeTablesByCourse(Course course) {
        return timeTableRepository.findByCourseId(course.getId());
    }
}
