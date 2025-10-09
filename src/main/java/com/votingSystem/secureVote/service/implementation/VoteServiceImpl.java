package com.votingSystem.secureVote.service.implementation;

import com.votingSystem.secureVote.entity.Candidates;
import com.votingSystem.secureVote.entity.Election;
import com.votingSystem.secureVote.entity.Users;
import com.votingSystem.secureVote.entity.Votes;
import com.votingSystem.secureVote.repository.CandidateRepository;
import com.votingSystem.secureVote.repository.ElectionRepository;
import com.votingSystem.secureVote.repository.UserRepository;
import com.votingSystem.secureVote.repository.VoteRepository;
import com.votingSystem.secureVote.service.VoteService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class VoteServiceImpl implements VoteService {

    private VoteRepository voteRepository;
    private CandidateRepository candidateRepository;
    private ElectionRepository electionRepository;
    private UserRepository userRepository;

    public VoteServiceImpl(VoteRepository voteRepository1, CandidateRepository candidates1,ElectionRepository electionRepository1, UserRepository userRepository1){
        this.voteRepository=voteRepository1;
        candidateRepository=candidates1;
        this.electionRepository=electionRepository1;
        this.userRepository=userRepository1;
    }


    @Override
    public Votes castVast(Long voterId, Long electionId, Long candidateId) {

        Votes newVote = new Votes();

        Candidates newCandidate ;
        newCandidate= candidateRepository.findById(candidateId).orElseThrow(()-> new RuntimeException("Candidate id not found "+candidateId));

        if (hasUserVoted(voterId,electionId)){
            newVote.setCandidates(newCandidate);

            Election newElection = electionRepository.findById(electionId).orElseThrow(()->new RuntimeException("Election not found : "+electionId));

            newVote.setElection(newElection);

            Users newUser  = userRepository.findById(voterId).orElseThrow(()->new RuntimeException("No voter found :"+ voterId));

            newVote.setUsers(newUser);
            newVote.setVoteCastAt(Timestamp.valueOf(LocalDateTime.now()));
        }

        return voteRepository.save(newVote);
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
