package com.votingSystem.secureVote.repository;

import com.votingSystem.secureVote.entity.MediaEvidence;
import com.votingSystem.secureVote.entity.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaEvidenceRepository  extends JpaRepository<MediaEvidence,Long> {

    List<MediaEvidence> findByVerificationId(Long verificationId);


}
