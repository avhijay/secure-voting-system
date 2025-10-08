package com.votingSystem.secureVote.service.implementation;

import com.votingSystem.secureVote.entity.Audit;
import com.votingSystem.secureVote.entity.Users;
import com.votingSystem.secureVote.repository.AuditRepository;
import com.votingSystem.secureVote.repository.UserRepository;
import com.votingSystem.secureVote.service.AuditService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class AuditServiceImpl implements AuditService {

    private AuditRepository auditRepository;
    private UserRepository userRepository;

    public AuditServiceImpl(AuditRepository theauditRepository,UserRepository theUserRepository){
        this.auditRepository=theauditRepository;
        this.userRepository=theUserRepository;

    }






    @Override
    public Audit logAction(Long userId, String action, String status, String reason) {
        Users theUser = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("Invalid Id "+userId));
Audit newAudit= new Audit();
newAudit.setId(userId);
newAudit.setAction(action);
newAudit.setStatus(status);
newAudit.setReason(reason);
newAudit.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

return auditRepository.save(newAudit);


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
        return auditRepository.findTopByOrderByIdDesc();
    }
}
