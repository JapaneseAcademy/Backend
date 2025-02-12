package com.academy.backend.dto.response.course;

import com.academy.backend.domain.course.Description;
import com.academy.backend.exception.course.DescriptionMappingException;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DescriptionResponse {

    private Long id;
    private String imageUrl;

    public static DescriptionResponse of(Description description) {
        try {
            return DescriptionResponse.builder()
                    .id(description.getId())
                    .imageUrl(description.getImageUrl())
                    .build();
        } catch (Exception e) {
            throw new DescriptionMappingException();
        }
    }
}
