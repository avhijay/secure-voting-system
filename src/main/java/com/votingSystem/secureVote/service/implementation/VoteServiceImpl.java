package com.votingSystem.secureVote.service.implementation;

import com.votingSystem.secureVote.entity.Votes;
import com.votingSystem.secureVote.service.VoteService;

public class VoteServiceImpl implements VoteService {
    @Override
    public Votes castVast(Long voterId, Long electionId, Long candidateId) {
        return null;
    }

    @Override
    public boolean hasUserVoted(Long voterId, Long electionId) {
        return false;
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
