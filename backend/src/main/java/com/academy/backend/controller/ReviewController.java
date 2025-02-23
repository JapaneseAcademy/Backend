package com.academy.backend.controller;

import com.academy.backend.dto.request.enrollment.ReviewCreateRequest;
import com.academy.backend.dto.response.enrollment.ReviewResponse;
import com.academy.backend.service.review.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
            @RequestPart("image") MultipartFile image
            ) {
        reviewService.createReview(authorizationHeader, request, image);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByCourseId(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Long courseId) {
        List<ReviewResponse> responses = reviewService.getReviewsByCourseId(courseId);

        return ResponseEntity.ok(responses);
    }

    @PostMapping("/tt")
    public ResponseEntity<?> testImage(@RequestPart("image") MultipartFile image) {
        if (image != null) {
            System.out.println(image);
        }
        return ResponseEntity.ok("test success");
    }
}
