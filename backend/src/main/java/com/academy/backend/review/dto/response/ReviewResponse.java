package com.academy.backend.review.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class ReviewResponse {

    private Long reviewId;
    private String title;
    private String review;
    private String name;
    private List<String> imageUrls;
    private Boolean isAnonymous;
    private LocalDate createdDate;
}
