package com.academy.backend.review.service;

import com.academy.backend.review.domain.Review;

import java.util.List;

public interface ReviewImageService {

    void createReviewImage(Review review, List<String> imageUrls);
}
