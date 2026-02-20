package com.example.portal.controller;

import com.example.portal.dto.CommentRequestDTO;
import com.example.portal.dto.CommentResponseDTO;
import com.example.portal.service.CommentService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/requests")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','AGENT')")
    @PostMapping("/{requestId}/comments")
    public CommentResponseDTO addComment(
            @PathVariable Long requestId,
            @RequestBody CommentRequestDTO dto) {

        return commentService.addComment(requestId, dto);
    }
}