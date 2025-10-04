package com.votingSystem.secureVote.repository;

import com.votingSystem.secureVote.entity.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationRepository extends JpaRepository<Verification,Long> {

     List<Verification> findByVoteId(Long voteId);

     List<Verification> findByUserId(Long userId);

     List<Verification> findByMethodAndStatus(String method , String status);
}
