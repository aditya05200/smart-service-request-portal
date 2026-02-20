package com.example.portal.service;

import com.example.portal.dto.CommentRequestDTO;
import com.example.portal.dto.CommentResponseDTO;
import com.example.portal.entity.Comment;
import com.example.portal.entity.ServiceRequest;
import com.example.portal.entity.User;
import com.example.portal.repository.CommentRepository;
import com.example.portal.repository.ServiceRequestRepository;
import com.example.portal.repository.UserRepository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ServiceRequestRepository serviceRequestRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository,
                          ServiceRequestRepository serviceRequestRepository,
                          UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.serviceRequestRepository = serviceRequestRepository;
        this.userRepository = userRepository;
    }

    public CommentResponseDTO addComment(Long requestId,
                                         CommentRequestDTO dto) {

        // ServiceRequest
        ServiceRequest serviceRequest =
                serviceRequestRepository.findById(requestId)
                        .orElseThrow(() ->
                                new RuntimeException("Request not found"));

        // Logged-in user
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // Save comment
        Comment comment = new Comment();
        comment.setServiceRequest(serviceRequest);
        comment.setUser(user);
        comment.setContent(dto.getContent());

        Comment saved = commentRepository.save(comment);

        return new CommentResponseDTO(
                saved.getId(),
                saved.getContent(),
                serviceRequest.getId(),
                user.getId()
        );
    }
}