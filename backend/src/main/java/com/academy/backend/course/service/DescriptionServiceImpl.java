package com.academy.backend.course.service;

import com.academy.backend.course.domain.Course;
import com.academy.backend.course.domain.Description;
import com.academy.backend.course.repository.DescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DescriptionServiceImpl implements DescriptionService {

    private final DescriptionRepository descriptionRepository;

    @Override
    @Transactional
    public void createDescription(Course course, List<String> imageUrls) {
        imageUrls.forEach(imageUrl -> {
            Description description = Description
                    .builder()
                    .course(course)
                    .imageUrl(imageUrl).build();
            descriptionRepository.save(description);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public List<Description> getDescriptionsByCourse(Course course) {
        return descriptionRepository.findDescriptionsByCourseId(course.getId());
    }
}
