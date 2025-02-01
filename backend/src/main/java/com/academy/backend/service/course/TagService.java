package com.academy.backend.service.course;

import com.academy.backend.dto.response.course.TagResponse;

import java.util.List;

public interface TagService {
    public List<TagResponse> getAllTags();
}
