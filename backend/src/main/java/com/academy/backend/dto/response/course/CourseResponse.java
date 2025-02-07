package com.academy.backend.dto.response.course;

import com.academy.backend.domain.course.Course;
import com.academy.backend.domain.course.Description;
import com.academy.backend.domain.course.Tag;
import com.academy.backend.domain.timetable.TimeTable;
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
    private List<DescriptionResponse> descriptions;
    private List<TimeTableResponse> timeTables;
    private List<TagResponse> tags;

    public static CourseResponse of(Course course, List<Description> descriptions, List<TimeTable> timeTables, List<Tag> tags) {
        try {
            return CourseResponse.builder()
                    .id(course.getId())
                    .title(course.getTitle())
                    .cost(course.getCost())
                    .startDate(course.getStartDate())
                    .endDate(course.getEndDate())
                    .descriptions(descriptions.stream()
                            .map(DescriptionResponse::of)
                            .toList())
                    .timeTables(timeTables.stream()
                            .map(TimeTableResponse::of)
                            .toList())
                    .tags(tags.stream()
                            .map(TagResponse::of)
                            .toList())
                    .build();
        } catch (Exception e) {
            throw new CourseMappingException();
        }
    }
}
