package com.votingSystem.secureVote.service.implementation;

import com.votingSystem.secureVote.entity.Votes;
import com.votingSystem.secureVote.repository.VoteRepository;
import com.votingSystem.secureVote.service.VoteService;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService {

    private VoteRepository voteRepository;

    public VoteServiceImpl(VoteRepository voteRepository1){
        this.voteRepository=voteRepository1;
    }


    @Override
    public Votes castVast(Long voterId, Long electionId, Long candidateId) {
        return null;
    }

    @Override
    public boolean hasUserVoted(Long voterId, Long electionId) {
        return voteRepository.findByUsersIdAndElectionId(voterId,electionId)!=null;

    }

    @Override
    public Long countVotesForCandidate(Long candidateId) {
        return 0L;
    }

    @Override
    public Long countVotesForElection(Long electionId) {
        return 0L;
    }
}
