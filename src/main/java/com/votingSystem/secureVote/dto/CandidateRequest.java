package com.votingSystem.secureVote.dto;

import java.sql.Timestamp;

public class CandidateRequest {
    private String name;
    private String partyName;
private  String bio;
    private String status;
    private Long electionId;
    private long approvedBy;
    private Timestamp createdAt;

    public CandidateRequest (){}


    public CandidateRequest(String name, String partyName, String bio, String status, Long electionId, long approvedBy, Timestamp createdAt) {
        this.name = name;
        this.partyName = partyName;
        this.bio = bio;
        this.status = status;
        this.electionId = electionId;
        this.approvedBy = approvedBy;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getElectionId() {
        return electionId;
    }

    public void setElectionId(Long electionId) {
        this.electionId = electionId;
    }

    public long getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(long approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
