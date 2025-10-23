package com.votingSystem.secureVote.service;

import com.votingSystem.secureVote.entity.Audit;

import java.util.List;
import java.util.Optional;

public interface AuditService {

    Audit logAction(Long userId, String action , String status, String reason);
    List<Audit> getAllAuditLogs();
    List<Audit> getAuditByUser(Long id);
    Audit getLatestAuditEntry();
     Optional<Long> verifyAuditChaining();






}
