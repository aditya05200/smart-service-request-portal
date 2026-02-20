package com.example.portal.repository;

import com.example.portal.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    List<AuditLog> findByServiceRequestId(Long serviceRequestId);
}
