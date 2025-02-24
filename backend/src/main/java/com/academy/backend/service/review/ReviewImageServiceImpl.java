package com.academy.backend.service.review;

import com.academy.backend.domain.review.Review;
import com.academy.backend.domain.review.ReviewImage;
import com.academy.backend.repository.ReviewImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

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
