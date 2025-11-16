package com.votingSystem.secureVote.dto.vote;

import jakarta.validation.constraints.NotNull;

public class GetByUserIdRequest {

    @NotNull(message = "userId cannot be null")
    private Long userId;

    public GetByUserIdRequest(){

    }

    public GetByUserIdRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
