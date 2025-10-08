package com.votingSystem.secureVote.repository;

import com.votingSystem.secureVote.entity.Votes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Votes,Long> {

    Votes findByUsersIdAndElectionId(Long voterId , Long electionId);
    List<Votes> findByElectionId(Long electionId);
    Long countByCandidatesId(Long candidateId);
    Long countByElectionId(Long electionId);

}

