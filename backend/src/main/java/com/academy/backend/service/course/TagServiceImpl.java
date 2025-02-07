package com.academy.backend.service.course;

import com.academy.backend.domain.course.Course;
import com.academy.backend.domain.course.Tag;
import com.academy.backend.dto.response.course.TagResponse;
import com.academy.backend.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    @Transactional
     public void createTags(Course course, List<String> tags) {
        tags.forEach(tag -> {
            tagRepository.save(Tag.builder().course(course).label(tag).build());
        });
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tag> getTagsByCourse(Course course) {
        return tagRepository.findTagsByCourseId(course.getId());
    }
}
