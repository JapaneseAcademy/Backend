package com.academy.backend.review.dto.response;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class BestReviewResponse extends ReviewResponse {

    private String courseTitle;

    BestReviewResponse(Long reviewId, String courseTitle, String title, String review, String name, List<String> imageUrls, Boolean isAnonymous, LocalDate createdDate) {
        super(reviewId, title, review, name, imageUrls, isAnonymous, createdDate);
        this.courseTitle = courseTitle;
    }
}
