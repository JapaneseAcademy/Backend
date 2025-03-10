package com.academy.backend.course.dto.response;

import com.academy.backend.timeTable.dto.response.TimeTableResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class CourseDetailResponse {

    private Long id;
    private String title;
    private Integer cost;
    private LocalDate startDate;
    private LocalDate endDate;
    private String mainImageUrl;
    private Boolean isLive;
    private Boolean isOnline;
    private Boolean isRecorded;
    private List<String> descriptions;
    private List<TimeTableResponse> timeTables;
    private int studentCount;
    private List<String> tags;
}
