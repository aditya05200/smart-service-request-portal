package com.example.portal.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.portal.dto.RequestStatusUpdateDTO;
import com.example.portal.dto.ServiceResponseDTO;
import com.example.portal.dto.ServiceRequestDTO;
import com.example.portal.service.ServiceRequestService;

@RestController
@RequestMapping("/api/requests")
public class ServiceRequestController {

    private final ServiceRequestService serviceRequestService;

    public ServiceRequestController(ServiceRequestService serviceRequestService) {
        this.serviceRequestService = serviceRequestService;
    }

    @PostMapping
    public ServiceResponseDTO createRequest(@RequestBody ServiceRequestDTO dto) {
        return serviceRequestService.createRequest(dto);
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('AGENT')")
    public ServiceResponseDTO updateRequestStatus(
            @PathVariable Long id,
            @RequestBody RequestStatusUpdateDTO dto) {

        return serviceRequestService.updateStatus(id, dto.getStatus());
    }

    @PutMapping("/{id}/assign/{agentId}")
    @PreAuthorize("hasRole('AGENT')")
    public ServiceResponseDTO assignRequest(
            @PathVariable Long id,
            @PathVariable Long agentId) {

        return serviceRequestService.assignRequest(id, agentId);
    }
}