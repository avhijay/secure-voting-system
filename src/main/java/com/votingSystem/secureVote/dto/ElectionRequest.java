package com.votingSystem.secureVote.dto;


import com.votingSystem.secureVote.entity.Users;

import java.security.Timestamp;

public class ElectionRequest {



    private String name;

    private String description;

    private String status;

    private java.sql.Timestamp endDate;

    private Users createdBy;

    private Timestamp createdAt;


    public ElectionRequest(){}


    public ElectionRequest(String name, String description, String status, java.sql.Timestamp endDate, Users createdBy, Timestamp createdAt) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.endDate = endDate;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public java.sql.Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(java.sql.Timestamp endDate) {
        this.endDate = endDate;
    }

    public Users getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
