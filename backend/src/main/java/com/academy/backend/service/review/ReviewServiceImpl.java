package com.academy.backend.service.review;

import com.academy.backend.domain.enrollment.Enrollment;
import com.academy.backend.domain.review.Review;
import com.academy.backend.dto.request.enrollment.ReviewCreateRequest;
import com.academy.backend.dto.response.enrollment.ReviewResponse;
import com.academy.backend.repository.ReviewRepository;
import com.academy.backend.service.common.S3Service;
import com.academy.backend.service.enrollment.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final EnrollmentService enrollmentService;
    private final ReviewImageService reviewImageService;
    private final S3Service s3Service;

    private final ReviewRepository reviewRepository;

    private Review saveReview(Enrollment enrollment, ReviewCreateRequest request){
        Review review = Review.builder()
                .enrollment(enrollment)
                .review(request.getReview())
                .isAnonymous(request.getIsAnonymous())
                .build();

        return reviewRepository.save(review);
    }

    private Review saveReview(Enrollment enrollment, String description, Boolean isAnonymous) {
        Review review = Review.builder()
                .enrollment(enrollment)
                .review(description)
                .isAnonymous(isAnonymous)
                .build();

        return reviewRepository.save(review);
    }

    @Override
    @Transactional
    public void createReview(String header, ReviewCreateRequest request, MultipartFile image) {
        // TODO: 수강내역-사용자가 맞는지 검증 로직 필요
        // TODO: 이미 리뷰가 있는 결제 내역이라면 예외 처리 추가
        Enrollment enrollment = enrollmentService.getEnrollmentEntityById(request.getEnrollmentId());
        Review review = saveReview(enrollment, request);

        if (image != null) {
            System.out.println("@@@@@ not null image");
        }
    }

    @Override
    @Transactional
    public void createReview(String header, Long enrollmentId, String description, Boolean isAnonymous, List<MultipartFile> images) {
        Enrollment enrollment = enrollmentService.getEnrollmentEntityById(enrollmentId);
        Review review = saveReview(enrollment, description, isAnonymous);

        if (!images.isEmpty()) {
            images.forEach(image -> {
                String imageUrl = s3Service.uploadImage(image);
                reviewImageService.createReviewImage(review, imageUrl);
            });
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponse> getAllReviews() {
        return reviewRepository.findAll().stream().map(ReviewResponse::of).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponse> getReviewsByCourseId(Long courseId) {
        return reviewRepository.findByCourseId(courseId).stream().map(ReviewResponse::of).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("no review"));
    }
}
