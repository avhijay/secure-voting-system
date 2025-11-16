package com.votingSystem.secureVote.dto.vote;

import jakarta.validation.constraints.NotNull;

public class GetByVoteIdRequest {



    @NotNull(message = "voteId cannot be null")
    private Long voteId;

    public GetByVoteIdRequest(){}

    public GetByVoteIdRequest(Long voteId) {
        this.voteId = voteId;
    }

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }
}