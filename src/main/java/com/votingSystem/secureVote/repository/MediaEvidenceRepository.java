package com.votingSystem.secureVote.repository;

import com.votingSystem.secureVote.entity.MediaEvidence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaEvidenceRepository  extends JpaRepository<MediaEvidence,Long> {

    List<MediaEvidence> findByVerification(String verificationId);

    List<MediaEvidence> findByMediaType(String type);
}
