package com.votingSystem.secureVote.service;

import com.votingSystem.secureVote.dto.vote.GetByElectionIdRequest;
import com.votingSystem.secureVote.dto.vote.GetByUserIdRequest;
import com.votingSystem.secureVote.dto.vote.GetByVoteIdRequest;
import com.votingSystem.secureVote.dto.vote.VoteResponse;
import com.votingSystem.secureVote.entity.Votes;

import java.util.List;

public interface VoteService {

    Votes castVote(Long voterId, Long electionId,Long candidateId);

    boolean hasUserVoted(Long voterId, Long electionId);

    Long countVotesForCandidate(Long candidateId);

    Long countVotesForElection(Long electionId);

    List<VoteResponse> returnAllVotesByElectionId(Long electionId);

    Votes  getVoteByVoteId( Long voteId);


    Votes getElectionVotesByVoterId(Long electionId , Long voterId);

    Votes getByUserId(Long userId);

    public Boolean hasTheUserVoted(Long electionId, Long userId);

    Long countForCandidateForElection(Long candidateId , Long ElectionId);






}
