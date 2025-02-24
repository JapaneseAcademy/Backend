package com.academy.backend.review.service;

import com.academy.backend.review.domain.Review;
import com.academy.backend.review.domain.ReviewImage;
import com.academy.backend.review.repository.ReviewImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewImageServiceImpl implements ReviewImageService{

    private final ReviewImageRepository reviewImageRepository;

    @Override
    public void createReviewImage(Review review, List<String> imageUrls) {
        imageUrls.stream()
                .map(imageUrl -> ReviewImage.builder()
                            .review(review)
                            .imageUrl(imageUrl)
                            .build())
                .forEach(reviewImageRepository::save);
    }
}
