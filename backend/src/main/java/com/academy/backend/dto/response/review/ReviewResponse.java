package com.academy.backend.dto.response.enrollment;

import com.academy.backend.domain.review.Review;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewResponse {

    private Long id;
    private String review;
    private Boolean isFeatured;
    private Boolean isAnonymous;
    private Boolean isVisible;

    public static ReviewResponse of(Review review) {
        if (review == null) {
            throw new RuntimeException("Review mapping exception");
        }

        return ReviewResponse.builder()
                .id(review.getId())
                .review(review.getReview())
                .isFeatured(review.getIsFeatured())
                .isAnonymous(review.getIsAnonymous())
                .isVisible(review.getIsVisible())
                .build();
    }
}
