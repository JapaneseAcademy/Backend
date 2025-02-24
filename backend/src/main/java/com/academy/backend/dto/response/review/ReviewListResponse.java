package com.academy.backend.dto.response.review;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ReviewListResponse {

    private List<ReviewResponse> reviewResponses;
    private String courseTitle;
    private Integer listSize;
    private Integer totalPage;
    private Long totalElements;
    private Boolean isFirst;
    private Boolean isLast;
}
