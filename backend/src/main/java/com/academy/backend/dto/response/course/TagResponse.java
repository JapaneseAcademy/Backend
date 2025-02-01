package com.academy.backend.dto.response.course;

import com.academy.backend.domain.course.Tag;
import com.academy.backend.exception.course.TagMappingException;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TagResponse {
    private String label;

    public static TagResponse of(Tag tag) {
        try {
            return TagResponse.builder()
                    .label(tag.getLabel())
                    .build();
        } catch (Exception e) {
            throw new TagMappingException();
        }
    }
}
