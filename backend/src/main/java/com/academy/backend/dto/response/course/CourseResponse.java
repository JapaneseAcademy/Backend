package com.academy.backend.dto.response.course;

import com.academy.backend.domain.course.Course;
import com.academy.backend.domain.course.CourseType;
import com.academy.backend.domain.course.Tag;
import com.academy.backend.domain.timetable.TimeTable;
import com.academy.backend.exception.course.CourseMappingException;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CourseResponse {

    private Long id;
    private String title;
    private String description;
    private List<TimeTableResponse> timeTables;
    private List<CourseTypeResponse> courseTypes;
    private List<TagResponse> tags;

    public static CourseResponse of(Course course, List<TimeTable> timeTables, List<CourseType> courseTypes, List<Tag> tags) {
        try {
            return CourseResponse.builder()
                    .id(course.getId())
                    .title(course.getTitle())
                    .description(course.getDescription())
                    .timeTables(timeTables.stream()
                            .map(TimeTableResponse::of)
                            .toList())
                    .courseTypes(courseTypes.stream()
                            .map(CourseTypeResponse::of)
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
