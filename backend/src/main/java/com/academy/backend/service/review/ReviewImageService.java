package com.academy.backend.service.review;

import com.academy.backend.domain.review.Review;

public interface ReviewImageService {

    void createReviewImage(Review review, String imageUrl);
}
