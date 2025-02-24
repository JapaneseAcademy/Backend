package com.academy.backend.course.service;

import com.academy.backend.course.domain.Course;
import com.academy.backend.course.domain.Tag;

import java.util.List;

public interface TagService {
    void createTags(Course course, List<String> tags);
    List<Tag> getTagsByCourse(Course course);
}
