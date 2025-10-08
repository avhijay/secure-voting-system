package com.votingSystem.secureVote.service;

import com.votingSystem.secureVote.entity.Audit;

import java.util.List;

public interface AuditService {

    Audit logAction(Long userId, String action , String status, String reason);
    List<Audit> getAllAuditLogs();
    List<Audit> getAuditByUser(Long id);
    Audit getLatestAuditEntry();


}
