package com.votingSystem.secureVote.dto;

import com.votingSystem.secureVote.entity.Election;
import com.votingSystem.secureVote.entity.Parties;
import jakarta.persistence.*;

import java.sql.Timestamp;

public class CandidateResponse {

    private String name;
    private String partyName;
    private String bio;
    private String status;
    private Long electionId;
    private long approvedBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Long id;
    private Long partyId;

    public CandidateResponse() {
    }

    public CandidateResponse(String name, String partyName, String bio, String status, Long electionId, long approvedBy, Timestamp createdAt, Timestamp updatedAt, Long id, Long partyId) {
        this.name = name;
        this.partyName = partyName;
        this.bio = bio;
        this.status = status;
        this.electionId = electionId;
        this.approvedBy = approvedBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.id = id;
        this.partyId = partyId;
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

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }
}