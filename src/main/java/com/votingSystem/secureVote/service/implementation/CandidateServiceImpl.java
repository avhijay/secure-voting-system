package com.votingSystem.secureVote.service.implementation;

import com.votingSystem.secureVote.entity.Candidates;
import com.votingSystem.secureVote.service.CandidateService;

import java.util.List;

public class CandidateServiceImpl implements CandidateService {
    @Override
    public List<Candidates> getCandidatesByElection(Long electionId) {
        return List.of();
    }

    @Override
    public List<Candidates> getCandidatesByParty(Long partyId) {
        return List.of();
    }

    @Override
    public Candidates approveCandidate(Long candidateId, Long userId) {
        return null;
    }

    @Override
    public Candidates getCandidateById(Long id) {
        return null;
    }

    @Override
    public List<Candidates> searchCandidateByName(String name) {
        return List.of();
    }
}
