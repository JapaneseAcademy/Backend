package com.academy.backend.review.converter;

import com.academy.backend.review.domain.Review;
import com.academy.backend.review.domain.ReviewImage;
import com.academy.backend.review.dto.response.BestReviewResponse;
import com.academy.backend.review.dto.response.ReviewListResponse;
import com.academy.backend.review.dto.response.ReviewResponse;
import com.academy.backend.exception.review.ReviewMappingException;
import org.springframework.data.domain.Page;

import java.util.List;

public class ReviewConverter {

    public static ReviewResponse toReviewResponse(Review review) {
        if (review == null) {
            throw new ReviewMappingException();
        }

        List<String> imageUrls = review.getReviewImages()
                .stream().map(ReviewImage::getImageUrl)
                .toList();

        return ReviewResponse.builder()
                .reviewId(review.getId())
                .title(review.getTitle())
                .review(review.getReview())
                .name("test")
                .imageUrls(imageUrls)
                .isAnonymous(review.getIsAnonymous())
                .createdDate(review.getCreatedAt().toLocalDate())
                .build();
    }

    public static ReviewListResponse toReviewListResponse(Page<Review> reviews, String courseTitle) {
        if (reviews == null) {
            throw new ReviewMappingException();
        }

        List<ReviewResponse> reviewResponses = reviews.stream()
                .map(ReviewConverter::toReviewResponse)
                .toList();

        return ReviewListResponse.builder()
                .reviewResponses(reviewResponses)
                .courseTitle(courseTitle)
                .listSize(reviewResponses.size())
                .totalPage(reviews.getTotalPages())
                .totalElements(reviews.getTotalElements())
                .isFirst(reviews.isFirst())
                .isLast(reviews.isLast())
                .build();
    }

    public static BestReviewResponse toBestReviewResponse(Review review) {
        if (review == null) {
            throw new ReviewMappingException();
        }



        return null;
    }
}
