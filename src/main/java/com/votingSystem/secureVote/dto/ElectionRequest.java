package com.votingSystem.secureVote.dto;


import com.votingSystem.secureVote.Enums.ElectionStatus;
import com.votingSystem.secureVote.entity.Users;

import java.security.Timestamp;

public class ElectionRequest {



    private String name;

    private String description;

    private ElectionStatus status;

    private java.sql.Timestamp endDate;

    private Users createdBy;

    private Timestamp createdAt;


    public ElectionRequest(){}


    public ElectionRequest(String name, Timestamp createdAt, Users createdBy, java.sql.Timestamp endDate, ElectionStatus status, String description) {
        this.name = name;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.endDate = endDate;
        this.status = status;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ElectionStatus getStatus() {
        return status;
    }

    public void setStatus(ElectionStatus status) {
        this.status = status;
    }

    public Users getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
    }

    public java.sql.Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(java.sql.Timestamp endDate) {
        this.endDate = endDate;
    }
}
