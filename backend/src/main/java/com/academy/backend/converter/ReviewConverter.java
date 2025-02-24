package com.academy.backend.converter;

import com.academy.backend.domain.review.Review;
import com.academy.backend.domain.review.ReviewImage;
import com.academy.backend.dto.response.review.BestReviewListResponse;
import com.academy.backend.dto.response.review.BestReviewResponse;
import com.academy.backend.dto.response.review.ReviewListResponse;
import com.academy.backend.dto.response.review.ReviewResponse;
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
                .title(review.getTitle())
                .review(review.getReview())
                .name(review.getName())
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
