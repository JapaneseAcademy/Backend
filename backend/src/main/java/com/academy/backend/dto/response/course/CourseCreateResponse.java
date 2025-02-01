package com.academy.backend.dto.response;

import com.academy.backend.domain.course.Course;
import com.academy.backend.exception.course.CourseMappingException;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CourseCreateResponse {

    private Long id;

    public static CourseCreateResponse of(Course course) {
        try {
            return CourseCreateResponse.builder()
                    .id(course.getId())
                    .build();
        } catch (Exception e) {
            throw new CourseMappingException();
        }
    }
}
