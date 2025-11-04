package com.votingSystem.secureVote.service.implementation;

import com.votingSystem.secureVote.entity.Candidates;
import com.votingSystem.secureVote.exception.AccessLevelNotSufficient;
import com.votingSystem.secureVote.exception.CandidateNotFound;
import com.votingSystem.secureVote.exception.ResourceNotFoundException;
import com.votingSystem.secureVote.repository.CandidateRepository;
import com.votingSystem.secureVote.security.AuthContext;
import com.votingSystem.secureVote.service.AuditService;
import com.votingSystem.secureVote.service.CandidateService;

import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;


@Service
public class CandidateServiceImpl implements CandidateService {


    private CandidateRepository candidateRepository;
    private AuditService auditService;

    public CandidateServiceImpl(CandidateRepository candidateRepository1 , AuditService auditService){
        this.candidateRepository=candidateRepository1;
        this.auditService=auditService;

    }

    @Override
    public List<Candidates> getCandidatesByElection(Long electionId) {
        Long LogsUserId = AuthContext.userId();

        int clearanceLevel = AuthContext.clearanceLevel();

        if (clearanceLevel<3) {
            throw new AccessLevelNotSufficient("Access level not sufficient");
        }

        if(candidateRepository.findByElectionId(electionId)==null){
            auditService.logAction(LogsUserId,"accessing candidates by election ","Rejected","No candidate found ");
            throw new ResourceNotFoundException("No candidate found with election id : "+electionId);

        }
auditService.logAction(LogsUserId,"accessing candidates by election ","Approved","none");
        return candidateRepository.findByElectionId(electionId) ;
    }

    @Override
    public List<Candidates> getCandidatesByParty(Long partyId) {
        Long LogsUserId = AuthContext.userId();

        int clearanceLevel = AuthContext.clearanceLevel();

        if (clearanceLevel<3) {
            throw new AccessLevelNotSufficient("Access level not sufficient");
        }

        if (candidateRepository.findByPartyId(partyId)==null){
            auditService.logAction(LogsUserId,"Accessing candidate through partyId","Rejected","No partyId found");
            throw  new ResourceNotFoundException("No candidate with the partyId"+partyId);

        }
        auditService.logAction(LogsUserId,"Accessing candidate through partyId","Approved","partyId found"+partyId);
        return candidateRepository.findByPartyId(partyId);
    }

    @Override
    public Candidates approveCandidate(Long candidateId, Long userId) {
        Long LogsUserId = AuthContext.userId();
        int clearanceLevel = AuthContext.clearanceLevel();

        if (clearanceLevel<3) {
            throw new AccessLevelNotSufficient("Access level not sufficient");
        }

        Candidates candidates  = candidateRepository.findById(candidateId).orElseThrow(()->new ResourceNotFoundException("No candidate found with the id :"+candidateId));
                    candidates.setApprovedBy(userId);
                    candidates.setStatus("Approved");
        auditService.logAction(LogsUserId,"candidate approval ","Approved","candidate id "+candidateId+"by user :"+userId);

                return candidateRepository.save(candidates);
    }

    @Override
    public Candidates rejectCandidate(Long candidateId) {
        Long LogsUserId = AuthContext.userId();

        int clearanceLevel = AuthContext.clearanceLevel();

        if (clearanceLevel<3) {
            throw new AccessLevelNotSufficient("Access level not sufficient");
        }

        Candidates candidates  = candidateRepository.findById(candidateId).orElseThrow(()->new ResourceNotFoundException("No candidate found with the id :"+candidateId));
        candidates.setStatus("Rejected");
        System.out.println("Candidate rejected :"+candidateId);
        auditService.logAction(LogsUserId,"RejectedCandidate","Approved","rejected candidate :"+candidateId);
        return candidateRepository.save(candidates);
    }

    @Override
    public Candidates getCandidateById(Long id) {
        Long LogsUserId = AuthContext.userId();

        int clearanceLevel = AuthContext.clearanceLevel();

        if (clearanceLevel<3) {
            throw new AccessLevelNotSufficient("Access level not sufficient");
        }

        auditService.logAction(LogsUserId,"Accessed candidate","Approved","Candidate "+id);
        return candidateRepository.findById(id).orElseThrow(()->new CandidateNotFound("The particular id not found : "+id));
    }

    @Override
    public List<Candidates> searchCandidateByName(String name) {
        Long LogsUserId = AuthContext.userId();

        int clearanceLevel = AuthContext.clearanceLevel();

        if (clearanceLevel<3) {
            throw new AccessLevelNotSufficient("Access level not sufficient");
        }

        if ( candidateRepository.findByNameContainingIgnoreCase(name)==null){
            auditService.logAction(LogsUserId,"candidate search by name ","Rejected","No candidate found"+name);
          throw new CandidateNotFound("Candidate Not Found with Name :"+name);
        }
        auditService.logAction(0L,"candidate search by name ","Approved","candidate found"+name);
        return candidateRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Candidates createCandidate(Candidates candidates) {
        Long LogsUserId = AuthContext.userId();
         int clearanceLevel = AuthContext.clearanceLevel();

         if (clearanceLevel<3) {
             throw new AccessLevelNotSufficient("Access level not sufficient");
         }
             auditService.logAction(LogsUserId, "candidate created  ", "Approved", "Candidate created " + candidates.getName());




        return candidateRepository.save(candidates);
    }


}
