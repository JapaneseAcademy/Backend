package com.academy.backend.review.service;

import com.academy.backend.common.service.CommonService;
import com.academy.backend.common.service.S3Service;
import com.academy.backend.course.domain.Course;
import com.academy.backend.course.service.CourseService;
import com.academy.backend.enrollment.domain.Enrollment;
import com.academy.backend.exception.review.DuplicateReviewException;
import com.academy.backend.exception.review.ReviewNotFoundException;
import com.academy.backend.review.converter.ReviewConverter;
import com.academy.backend.review.domain.Review;
import com.academy.backend.review.dto.request.ReviewCreateRequest;
import com.academy.backend.review.dto.response.ReviewListResponse;
import com.academy.backend.review.dto.response.ReviewResponse;
import com.academy.backend.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final CommonService commonService;
    private final ReviewImageService reviewImageService;
    private final CourseService courseService;
    private final S3Service s3Service;

    private final ReviewRepository reviewRepository;

    private final String path = "reviews/";

    private Review saveReview(Enrollment enrollment, ReviewCreateRequest request){
        Review review = Review.builder()
                .enrollment(enrollment)
                .title(request.getTitle())
                .review(request.getReview())
                .isAnonymous(request.getIsAnonymous())
                .build();

        return reviewRepository.save(review);
    }

    @Override
    @Transactional
    public void createReview(String header, ReviewCreateRequest request, List<MultipartFile> images) {
        // TODO: 수강내역-사용자가 맞는지 검증 로직 필요
        String memberName = "dding";

        validateWithEnrollmentId(request.getEnrollmentId());

        Enrollment enrollment = commonService.getEnrollmentByEnrollmentId(request.getEnrollmentId());
        Review review = saveReview(enrollment, request);

        if (images != null && !images.isEmpty()) {
            List<String> imageUrls = images.stream()
                    .map(image -> s3Service.uploadImage(image, path))
                    .toList();

            reviewImageService.createReviewImage(review, imageUrls);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponse> getAllReviews(Integer page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Review> reviews = reviewRepository.findAll(pageable);
//        ReviewConverter.toReviewListResponse(reviews);
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public ReviewListResponse getReviewsByCourseId(Integer page, Long courseId) {
        Course course = courseService.findCourse(courseId);

        Pageable pageable = PageRequest.of(page, 5);
        Page<Review> reviews = reviewRepository.findByCourseId(courseId, pageable);

        return ReviewConverter.toReviewListResponse(reviews, course.getTitle());
    }

    @Override
    @Transactional(readOnly = true)
    public Review getReviewByEnrollmentId(Long enrollmentId) {
        return reviewRepository.findByEnrollmentId(enrollmentId).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean getExistsByEnrollmentId(Long enrollmentId) {
        return reviewRepository.existsByEnrollmentId(enrollmentId);
    }

    private void validateWithEnrollmentId(Long enrollmentId) {
        if (reviewRepository.existsByEnrollmentId(enrollmentId)) {
            throw new DuplicateReviewException(enrollmentId);
        }
    }
}
