package com.academy.backend.service.review;

import com.academy.backend.domain.review.Review;
import com.academy.backend.domain.review.ReviewImage;
import com.academy.backend.repository.ReviewImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewImageServiceImpl implements ReviewImageService{

    private final ReviewImageRepository reviewImageRepository;

    @Override
    @Transactional
    public void createReviewImage(Review review, String imageUrl) {
        ReviewImage reviewImage = ReviewImage.builder()
                .review(review)
                .imageUrl(imageUrl)
                .build();
        reviewImageRepository.save(reviewImage);
    }
}
