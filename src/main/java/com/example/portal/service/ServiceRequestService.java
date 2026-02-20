package com.example.portal.service;

import org.springframework.stereotype.Service;

import com.example.portal.dto.ServiceResponseDTO;
import com.example.portal.dto.ServiceRequestDTO;
import com.example.portal.entity.ServiceRequest;
import com.example.portal.entity.User;
import com.example.portal.repository.ServiceRequestRepository;
import com.example.portal.repository.UserRepository;

@Service
public class ServiceRequestService {

    private final ServiceRequestRepository serviceRequestRepository;
    private final UserRepository userRepository;
    private final AuditLogService auditLogService;

    public ServiceRequestService(ServiceRequestRepository serviceRequestRepository,
                                 UserRepository userRepository,
                                 AuditLogService auditLogService) {
        this.serviceRequestRepository = serviceRequestRepository;
        this.userRepository = userRepository;
        this.auditLogService = auditLogService;
    }

    public ServiceResponseDTO createRequest(ServiceRequestDTO dto) {

        ServiceRequest request = new ServiceRequest();
        request.setTitle(dto.getTitle());
        request.setDescription(dto.getDescription());
        request.setStatus("OPEN");

        ServiceRequest saved = serviceRequestRepository.save(request);

        auditLogService.logAction("REQUEST_CREATED", saved, null);

        return new ServiceResponseDTO(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                saved.getStatus()
        );
    }

    public ServiceResponseDTO updateStatus(Long id, String status) {

        ServiceRequest request = serviceRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus(status);
        ServiceRequest updated = serviceRequestRepository.save(request);

        auditLogService.logAction("STATUS_UPDATED", updated, null);

        return new ServiceResponseDTO(
                updated.getId(),
                updated.getTitle(),
                updated.getDescription(),
                updated.getStatus()
        );
    }

    public ServiceResponseDTO assignRequest(Long requestId, Long agentId) {

        ServiceRequest request = serviceRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        User agent = userRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent not found"));

        request.setAssignedAgent(agent);
        ServiceRequest updated = serviceRequestRepository.save(request);

        auditLogService.logAction("REQUEST_ASSIGNED", updated, agent);

        return new ServiceResponseDTO(
                updated.getId(),
                updated.getTitle(),
                updated.getDescription(),
                updated.getStatus()
        );
    }
}