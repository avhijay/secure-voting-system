package com.votingSystem.secureVote.service.implementation;

import com.votingSystem.secureVote.entity.Candidates;
import com.votingSystem.secureVote.repository.CandidateRepository;
import com.votingSystem.secureVote.service.CandidateService;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CandidateServiceImpl implements CandidateService {


    private CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository1){
        this.candidateRepository=candidateRepository1;
    }

    @Override
    public List<Candidates> getCandidatesByElection(Long electionId) {
        return candidateRepository.findByElectionId(electionId) ;
    }

    @Override
    public List<Candidates> getCandidatesByParty(Long partyId) {
        return candidateRepository.findByPartyId(partyId);
    }

    @Override
    public Candidates approveCandidate(Long candidateId, Long userId) {
        return null;
    }

    @Override
    public Candidates getCandidateById(Long id) {
        return candidateRepository.findById(id).orElseThrow(()->new RuntimeException("The particular id not found : "+id));
    }

    @Override
    public List<Candidates> searchCandidateByName(String name) {
        return candidateRepository.findByNameContainingIgnoreCase(name);
    }
}
