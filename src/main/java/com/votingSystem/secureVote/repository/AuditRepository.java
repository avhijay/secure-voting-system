package com.votingSystem.secureVote.repository;

import com.votingSystem.secureVote.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditRepository extends JpaRepository<Audit,Long> {

    Audit findTopByOrderIdDesc();
    List<Audit>findByUserIdOrderByCreatedAtDesc(Long userId);
    List<Audit>findByStatus(String status);
}
