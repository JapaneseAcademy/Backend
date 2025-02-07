package com.academy.backend.service.course;

import com.academy.backend.domain.course.Course;
import com.academy.backend.domain.course.Tag;

import java.util.List;

public interface TagService {
    void createTags(Course course, List<String> tags);
    List<Tag> getTagsByCourse(Course course);
}
