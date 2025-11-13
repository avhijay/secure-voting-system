package com.votingSystem.secureVote.dto;

import com.votingSystem.secureVote.Enums.ElectionStatus;
import com.votingSystem.secureVote.entity.Users;

import java.sql.Timestamp;

public class ElectionResponse {

    private String name;


    private String description;

    private ElectionStatus status;


    private Timestamp startDate;


    private Timestamp endDate;


    private Users createdBy;


    private Timestamp createdAt;

    private Timestamp updatedAt;


    public ElectionResponse() {
    }

    public ElectionResponse(Timestamp updatedAt, Timestamp createdAt, Users createdBy, Timestamp endDate, Timestamp startDate, ElectionStatus status, String description, String name) {
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.endDate = endDate;
        this.startDate = startDate;
        this.status = status;
        this.description = description;
        this.name = name;
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

    public ElectionStatus getStatus() {
        return status;
    }

    public void setStatus(ElectionStatus status) {
        this.status = status;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
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

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
