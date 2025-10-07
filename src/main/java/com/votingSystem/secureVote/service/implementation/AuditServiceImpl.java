package com.votingSystem.secureVote.service.implementation;

import com.votingSystem.secureVote.entity.Audit;
import com.votingSystem.secureVote.repository.AuditRepository;
import com.votingSystem.secureVote.service.AuditService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuditServiceImpl implements AuditService {

    private AuditRepository auditRepository;

    public AuditServiceImpl(AuditRepository theauditRepository){
        this.auditRepository=theauditRepository;

    }



    @Override
    public Audit logAction(Long userId, String action, String status, String reason) {
        return null;
    }

    @Override
    public List<Audit> getAllAuditLogs() {
        return auditRepository.findAll();
    }

    @Override
    public List<Audit> getAuditByUser(Long userId) {
        return auditRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    @Override
    public Audit getLatestAuditEntry() {
        return null;
    }
}
