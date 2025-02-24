package com.academy.backend.review.service;

import com.academy.backend.review.domain.Review;
import com.academy.backend.review.dto.request.ReviewCreateRequest;
import com.academy.backend.review.dto.response.ReviewListResponse;
import com.academy.backend.review.dto.response.ReviewResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewService {
    void createReview(String header, ReviewCreateRequest request, List<MultipartFile> images);

    List<ReviewResponse> getAllReviews(Integer page);
    ReviewListResponse getReviewsByCourseId(Integer page, Long courseId);
    Review getReviewById(Long reviewId);
}
