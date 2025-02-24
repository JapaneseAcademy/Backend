package com.academy.backend.service.review;

import com.academy.backend.domain.review.Review;
import com.academy.backend.dto.request.review.ReviewCreateRequest;
import com.academy.backend.dto.response.review.ReviewListResponse;
import com.academy.backend.dto.response.review.ReviewResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewService {
    void createReview(String header, ReviewCreateRequest request, List<MultipartFile> images);

    List<ReviewResponse> getAllReviews(Integer page);
    ReviewListResponse getReviewsByCourseId(Integer page, Long courseId);
    Review getReviewById(Long reviewId);
}
