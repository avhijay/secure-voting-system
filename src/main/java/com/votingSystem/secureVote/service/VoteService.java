package com.votingSystem.secureVote.service;

import com.votingSystem.secureVote.entity.Votes;

import java.util.List;
import java.util.Optional;

public interface VoteService {

    Votes castVote(Long voterId, Long electionId,Long candidateId);

    boolean hasUserVoted(Long voterId, Long electionId);

    Long countVotesForCandidate(Long candidateId);

    Long countVotesForElection(Long electionId);

    List<Votes> returnAllVotesByElectionId(Long electionId);

    Votes  getVoteByVoteId(Long voteId);


    Votes getElectionVotesByVoterId(Long electionId , Long voterId);

    Votes getByUserId(Long userId);





}
