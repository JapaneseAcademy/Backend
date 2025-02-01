package com.academy.backend.dto.response.course;

import com.academy.backend.domain.course.Category;
import com.academy.backend.domain.course.CourseType;
import com.academy.backend.exception.course.CourseTypeMappingException;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CourseTypeResponse {

    private Category category;
    private int cost;

    public static CourseTypeResponse of(CourseType courseType) {
        try {
            return CourseTypeResponse.builder()
                    .category(courseType.getCategory())
                    .cost(courseType.getCost())
                    .build();
        } catch (Exception e) {
            throw new CourseTypeMappingException();
        }
    }
}
