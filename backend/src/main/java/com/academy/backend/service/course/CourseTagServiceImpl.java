package com.academy.backend.service.course;

import com.academy.backend.domain.course.Course;
import com.academy.backend.domain.course.CourseTag;
import com.academy.backend.domain.course.Tag;
import com.academy.backend.repository.CourseTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseTagServiceImpl implements CourseTagService {

    private final CourseTagRepository courseTagRepository;

    @Override
    public void craeteCourseTag(Course course, List<Tag> tags) {
        tags.forEach(tag -> courseTagRepository
                .save(CourseTag.builder()
                        .course(course)
                        .tag(tag)
                        .build()
                ));
    }
}
