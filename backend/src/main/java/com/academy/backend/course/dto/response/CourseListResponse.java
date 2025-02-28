package com.academy.backend.course.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class CourseListResponse {

    private List<CourseInfo> courseInfos;
    private int listSize;

    @Getter
    @Builder
    public static class CourseInfo {
        private Long courseId;
        private String title;
        private Integer cost;
        private LocalDate startDate;
        private LocalDate endDate;
        private String mainImageUrl;
        private List<String> descriptions;
        private Boolean isLive;
        private Boolean isOnline;
        private Boolean isRecorded;
        private int studentCount;
    }
}
