package com.votingSystem.secureVote.dto.vote;

import com.votingSystem.secureVote.entity.Votes;

import java.sql.Timestamp;

public class VoteResponse {
    private  Long voteId;
    private Long voterId;
    private Long electionId;
    private Long candidateId;
    private Timestamp castAt;




    public VoteResponse (){}




    public  VoteResponse(Votes votes){
        this.voteId = votes.getId();
        this.voterId = votes.getUsers().getId();
        this.electionId = votes.getElection().getId();
        this.candidateId = votes.getCandidates().getId();
        this.castAt = votes.getVoteCastAt();

    }


    public VoteResponse(Long voteId, Long voterId, Long electionId, Long candidateId, Timestamp castAt) {
        this.voteId = voteId;
        this.voterId = voterId;
        this.electionId = electionId;
        this.candidateId = candidateId;
        this.castAt = castAt;
    }


    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public Long getVoterId() {
        return voterId;
    }

    public void setVoterId(Long voterId) {
        this.voterId = voterId;
    }

    public Long getElectionId() {
        return electionId;
    }

    public void setElectionId(Long electionId) {
        this.electionId = electionId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public Timestamp getCastAt() {
        return castAt;
    }

    public void setCastAt(Timestamp castAt) {
        this.castAt = castAt;
    }
}
