package com.academy.backend.service.course;

import com.academy.backend.domain.course.Course;
import com.academy.backend.domain.course.Tag;

import java.util.List;

public interface CourseTagService {
    public void createCourseTag(Course course, List<Tag> tags);

    public List<Tag> getTagsByCourse(Course course);
}
