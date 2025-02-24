package com.academy.backend.dto.response.review;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BestReviewListResponse {

    private List<BestReviewResponse> reviewResponses;
    private Integer listSize;
    private Integer totalPage;
    private Long totalElements;
    private Boolean isFirst;
    private Boolean isLast;
}
