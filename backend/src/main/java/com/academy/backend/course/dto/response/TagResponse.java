package com.academy.backend.course.dto.response;

import com.academy.backend.course.domain.Tag;
import com.academy.backend.exception.course.TagMappingException;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TagResponse {

    private Long id;
    private String label;

    public static TagResponse of(Tag tag) {
        try {
            return TagResponse.builder()
                    .id(tag.getId())
                    .label(tag.getLabel())
                    .build();
        } catch (Exception e) {
            throw new TagMappingException();
        }
    }
}
