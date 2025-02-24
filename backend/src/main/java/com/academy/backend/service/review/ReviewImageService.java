package com.academy.backend.service.review;

import com.academy.backend.domain.review.Review;

import java.util.List;

public interface ReviewImageService {

    void createReviewImage(Review review, List<String> imageUrls);
}
