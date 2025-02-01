package com.academy.backend.controller;

import com.academy.backend.dto.response.course.TagResponse;
import com.academy.backend.service.course.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("")
    public ResponseEntity<?> getAllTags() {
        List<TagResponse> responses = tagService.getAllTags();

        return ResponseEntity.ok(responses);
    }
}
