package com.votingSystem.secureVote.service.implementation;

import com.votingSystem.secureVote.entity.Audit;
import com.votingSystem.secureVote.service.AuditService;

import java.util.List;

public class AuditServiceImpl implements AuditService {
    @Override
    public Audit logAction(Long userId, String action, String status, String reason) {
        return null;
    }

    @Override
    public List<Audit> getAllAuditLogs() {
        return List.of();
    }

    @Override
    public List<Audit> getAuditByUser(Long userId) {
        return List.of();
    }

    @Override
    public Audit getLatestAuditEntry() {
        return null;
    }
}
