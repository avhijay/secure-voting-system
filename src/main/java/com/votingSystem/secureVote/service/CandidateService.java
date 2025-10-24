package com.votingSystem.secureVote.service;

import com.votingSystem.secureVote.entity.Candidates;
import org.hibernate.boot.model.source.internal.hbm.RelationalObjectBinder;

import java.util.List;

public interface CandidateService {

    List<Candidates> getCandidatesByElection(Long electionId);

    List<Candidates> getCandidatesByParty(Long partyId);

    Candidates approveCandidate(Long candidateId, Long userId);
    Candidates rejectCandidate(Long candidateId);

    Candidates getCandidateById(Long id);

    List<Candidates> searchCandidateByName(String name);
Candidates createCandidate(Candidates candidates);



}
