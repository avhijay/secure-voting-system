package com.votingSystem.secureVote.dto;

import jakarta.validation.constraints.NotNull;

public class VoteRequest {
    @NotNull(message = "voterId cant be null")
    private Long voterId;

    @NotNull(message = "electionId cant be null")
    private Long electionId;

    @NotNull(message = "candidateId cant be null")
    private Long candidateId;


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
}
