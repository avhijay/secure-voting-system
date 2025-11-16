package com.votingSystem.secureVote.dto.vote;

import jakarta.validation.constraints.NotNull;

public class GetByElectionIdRequest {


    @NotNull(message = "electionId cannot be null")
    private Long electionId;

    public GetByElectionIdRequest(){}

    public Long getElectionId() {
        return electionId;
    }

    public void setElectionId(Long electionId) {
        this.electionId = electionId;
    }
}
