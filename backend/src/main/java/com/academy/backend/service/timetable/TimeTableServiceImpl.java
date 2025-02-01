package com.academy.backend.service.timetable;

import com.academy.backend.domain.course.Course;
import com.academy.backend.domain.timetable.TimeTable;
import com.academy.backend.dto.request.CourseTimeTableRequest;
import com.academy.backend.repository.TimeTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeTableServiceImpl implements TimeTableService {

    private final TimeTableRepository timeTableRepository;

    @Override
    @Transactional
    public List<TimeTable> createTimeTable(Course course, List<CourseTimeTableRequest> requests) {
        List<TimeTable> timeTables = new ArrayList<>();

        requests.forEach(schedule -> {
            TimeTable timeTable = TimeTable.builder()
                    .course(course)
                    .weekday(schedule.getWeekday())
                    .startTime(schedule.getStartTime())
                    .endTime(schedule.getEndTime())
                    .classroom(schedule.getClassroom())
                    .build();
            TimeTable saved = timeTableRepository.save(timeTable);
            timeTables.add(saved);
        });

        return timeTables;
    }
}
