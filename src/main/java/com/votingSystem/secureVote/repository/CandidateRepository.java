package com.votingSystem.secureVote.repository;

import com.votingSystem.secureVote.entity.Candidates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidates,Long> {

  List<Candidates> findByElectionId(Long electionId);

  List<Candidates> findByPartyId(Long partyId);

  List<Candidates> findByApprovedById(Long userId);

  List<Candidates> findByNameContainingIgnoreCase(String partialName);
}
