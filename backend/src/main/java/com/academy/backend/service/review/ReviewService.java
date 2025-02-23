package com.academy.backend.service.review;

import com.academy.backend.domain.review.Review;
import com.academy.backend.dto.request.enrollment.ReviewCreateRequest;
import com.academy.backend.dto.response.enrollment.ReviewResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewService {
    void createReview(String header, ReviewCreateRequest request, MultipartFile image);

    void createReview(String header, Long enrollmentId, String review, Boolean isAnonymous, List<MultipartFile> images);
    List<ReviewResponse> getAllReviews();
    List<ReviewResponse> getReviewsByCourseId(Long courseId);
    Review getReviewById(Long reviewId);
}
