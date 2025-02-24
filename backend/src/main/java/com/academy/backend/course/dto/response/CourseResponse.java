package com.academy.backend.course.dto.response;

import com.academy.backend.course.domain.Course;
import com.academy.backend.course.domain.Description;
import com.academy.backend.course.domain.Tag;
import com.academy.backend.timeTable.domain.TimeTable;
import com.academy.backend.exception.course.CourseMappingException;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class CourseResponse {

    private Long id;
    private String title;
    private Integer cost;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isLive;
    private Boolean isOnline;
    private Boolean isRecorded;
    private List<String> descriptions;
    private List<TimeTableResponse> timeTables;
    private List<String> tags;
}
