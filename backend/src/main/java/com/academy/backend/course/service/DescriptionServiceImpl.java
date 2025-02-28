package com.academy.backend.course.service;

import com.academy.backend.common.service.S3Service;
import com.academy.backend.course.domain.Course;
import com.academy.backend.course.domain.Description;
import com.academy.backend.course.repository.DescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DescriptionServiceImpl implements DescriptionService {

    private final S3Service s3Service;

    private final DescriptionRepository descriptionRepository;

    private static final String path = "courses/";

    @Override
    @Transactional
    public void createDescription(Course course, List<MultipartFile> imageDescriptions) {
        imageDescriptions.forEach(imageDescription -> {
            String imageUrl = s3Service.uploadImage(imageDescription, path + course.getId() + "/");
            Description description = Description.builder()
                    .course(course)
                    .imageUrl(imageUrl)
                    .build();
            descriptionRepository.save(description);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public List<Description> getDescriptionsByCourse(Course course) {
        return descriptionRepository.findDescriptionsByCourseId(course.getId());
    }
}
