package com.example.portal.service;

import com.example.portal.entity.AuditLog;
import com.example.portal.entity.ServiceRequest;
import com.example.portal.entity.User;
import com.example.portal.repository.AuditLogRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public void logAction(String action,
                          ServiceRequest serviceRequest,
                          User user) {

        AuditLog log = new AuditLog();
        log.setAction(action);
        log.setServiceRequest(serviceRequest);
        log.setPerformedBy(user);
        log.setCreatedAt(LocalDateTime.now());

        auditLogRepository.save(log);
    }
}