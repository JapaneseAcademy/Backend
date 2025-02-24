package com.academy.backend.review.controller;

import com.academy.backend.review.dto.request.ReviewCreateRequest;
import com.academy.backend.review.dto.response.ReviewListResponse;
import com.academy.backend.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping(value = "")
    public ResponseEntity<?> createReview(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestPart("request") ReviewCreateRequest request,
            @RequestPart(value = "images", required = false) List<MultipartFile> images
            ) {
        reviewService.createReview(authorizationHeader, request, images);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("")
    public ResponseEntity<ReviewListResponse> getReviewsByCourseId(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam("courseId") Long courseId,
            @RequestParam(value = "page", defaultValue = "0") Integer page
            ) {
        ReviewListResponse responses = reviewService.getReviewsByCourseId(page, courseId);

        return ResponseEntity.ok(responses);
    }
}
