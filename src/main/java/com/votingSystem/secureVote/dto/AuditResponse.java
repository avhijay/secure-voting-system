package com.votingSystem.secureVote.dto;

public class AuditResponse {
    private String status;
        private Long brokenAtId;
        private String message;


        public AuditResponse(){}

    public AuditResponse(String status, Long brokenAtId, String message) {
        this.status = status;
        this.brokenAtId = brokenAtId;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getBrokenAtId() {
        return brokenAtId;
    }

    public void setBrokenAtId(Long brokenAtId) {
        this.brokenAtId = brokenAtId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

