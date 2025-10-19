package com.votingSystem.secureVote.dto;

import jakarta.validation.constraints.NotNull;

public class UserResponse {



    private String name;

    private String role;

    private String status;

    private Integer clearanceLevel;

    private String emailId;


    public Integer getClearanceLevel() {
        return clearanceLevel;
    }

    public void setClearanceLevel(Integer clearanceLevel) {
        this.clearanceLevel = clearanceLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
