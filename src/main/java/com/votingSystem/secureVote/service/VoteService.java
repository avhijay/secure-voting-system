package com.votingSystem.secureVote.service;

import com.votingSystem.secureVote.entity.Votes;

public interface VoteService {

    Votes castVote(Long voterId, Long electionId,Long candidateId);

    boolean hasUserVoted(Long voterId, Long electionId);

    Long countVotesForCandidate(Long candidateId);

    Long countVotesForElection(Long electionId);




}
