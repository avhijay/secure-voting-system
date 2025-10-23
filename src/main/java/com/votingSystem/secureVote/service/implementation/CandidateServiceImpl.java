package com.votingSystem.secureVote.service.implementation;

import com.votingSystem.secureVote.entity.Candidates;
import com.votingSystem.secureVote.exception.CandidateNotFound;
import com.votingSystem.secureVote.exception.ResourceNotFoundException;
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
        if(candidateRepository.findByElectionId(electionId)==null){
            throw new ResourceNotFoundException("No candidate found with election id : "+electionId);
        }
        return candidateRepository.findByElectionId(electionId) ;
    }

    @Override
    public List<Candidates> getCandidatesByParty(Long partyId) {
        if (candidateRepository.findByPartyId(partyId)==null){
            throw  new ResourceNotFoundException("No candidate with the partyId"+partyId);
        }
        return candidateRepository.findByPartyId(partyId);
    }

    @Override
    public Candidates approveCandidate(Long candidateId, Long userId) {
        return null;
    }

    @Override
    public Candidates getCandidateById(Long id) {
        return candidateRepository.findById(id).orElseThrow(()->new CandidateNotFound("The particular id not found : "+id));
    }

    @Override
    public List<Candidates> searchCandidateByName(String name) {
        if ( candidateRepository.findByNameContainingIgnoreCase(name)==null){
          throw new CandidateNotFound("Candidate Not Found with Name :"+name);
        }
        return candidateRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Candidates createCandidate(Candidates candidates) {
        return candidateRepository.save(candidates);
    }


}
