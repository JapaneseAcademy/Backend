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
