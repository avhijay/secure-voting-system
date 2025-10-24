package com.votingSystem.secureVote.dto;

import java.sql.Timestamp;

public class CandidateRequest {
    private String name;
    private String partyName;
private  String bio;
private Long candidateId;
    private String status;
    private Long electionId;
    private long approvedBy;
    private Timestamp createdAt;
    private Long userId;

    public CandidateRequest (){}


    public CandidateRequest(String name, String partyName, String bio, Long candidateId, String status, Long electionId, long approvedBy, Timestamp createdAt, Long userId) {
        this.name = name;
        this.partyName = partyName;
        this.bio = bio;
        this.candidateId = candidateId;
        this.status = status;
        this.electionId = electionId;
        this.approvedBy = approvedBy;
        this.createdAt = createdAt;
        this.userId = userId;
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

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
