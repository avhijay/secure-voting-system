package com.votingSystem.secureVote.dto;

public class VoteResponse {
    private  Long voteId;
    private Long voterId;
    private Long electionId;
    private Long candidateId;
    private String castAt;


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

    public String getCastAt() {
        return castAt;
    }

    public void setCastAt(String castAt) {
        this.castAt = castAt;
    }
}
