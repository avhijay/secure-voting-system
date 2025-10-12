package com.votingSystem.secureVote.service.implementation;

import com.votingSystem.secureVote.entity.Candidates;
import com.votingSystem.secureVote.entity.Election;
import com.votingSystem.secureVote.entity.Users;
import com.votingSystem.secureVote.entity.Votes;
import com.votingSystem.secureVote.repository.CandidateRepository;
import com.votingSystem.secureVote.repository.ElectionRepository;
import com.votingSystem.secureVote.repository.UserRepository;
import com.votingSystem.secureVote.repository.VoteRepository;
import com.votingSystem.secureVote.service.AuditService;
import com.votingSystem.secureVote.service.VoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class VoteServiceImpl implements VoteService {

    private VoteRepository voteRepository;
    private CandidateRepository candidateRepository;
    private ElectionRepository electionRepository;
    private UserRepository userRepository;
    private AuditService auditService;

    public VoteServiceImpl(VoteRepository voteRepository1, CandidateRepository candidates1,ElectionRepository electionRepository1, UserRepository userRepository1 , AuditService auditService1){
        this.voteRepository=voteRepository1;
        candidateRepository=candidates1;
        this.electionRepository=electionRepository1;
        this.userRepository=userRepository1;
        this.auditService=auditService1;
    }

@Transactional
    @Override
    public Votes castVote(Long voterId, Long electionId, Long candidateId) {

        Votes newVote = new Votes();

        Candidates newCandidate ;
        newCandidate= candidateRepository.findById(candidateId).orElseThrow(()-> new RuntimeException("Candidate id not found "+candidateId));


if(!newCandidate.getElection().getId().equals(electionId)){
    auditService.logAction(voterId,"Cast_vote","Failure","wrong candidate selection (Mismatch with election id ) ");
    throw new RuntimeException("Candidate not available for the current election: "+candidateId+"in election: "+electionId);
}
        if (!hasUserVoted(voterId,electionId)){
            newVote.setCandidates(newCandidate);

            Election newElection = electionRepository.findById(electionId).orElseThrow(()->new RuntimeException("Election not found : "+electionId));

            if (!newElection.getStatus().equalsIgnoreCase("ONGOING")){
                auditService.logAction(voterId,"Cast_vote","Failure","Election not active :"+electionId);
                throw new RuntimeException("Election is currently not active :"+electionId);
            }

            newVote.setElection(newElection);

            Users newUser  = userRepository.findById(voterId).orElseThrow(()->new RuntimeException("No voter found :"+ voterId));

            newVote.setUsers(newUser);
            newVote.setVoteCastAt(Timestamp.valueOf(LocalDateTime.now()));
        }   else{
            auditService.logAction(voterId,"Casting vote ","Failure","");
            throw new RuntimeException("User has already Voted");
        }
        Votes saved = voteRepository.save(newVote);
auditService.logAction(voterId,"Casting vote","Sucess","Voting process by the user "); voteRepository.save(newVote);
return  saved;
    }

    @Override
    public boolean hasUserVoted(Long voterId, Long electionId) {
        return voteRepository.findByUsersIdAndElectionId(voterId,electionId)!=null;

    }

    @Override
    public Long countVotesForCandidate(Long candidateId) {
        return voteRepository.countByCandidatesId(candidateId);
        
    }

    @Override
    public Long countVotesForElection(Long electionId) {
        return voteRepository.countByElectionId(electionId);
    }
}
