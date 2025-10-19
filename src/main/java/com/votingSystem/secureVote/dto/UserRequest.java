package com.votingSystem.secureVote.dto;

import jakarta.validation.constraints.NotNull;

public class UserRequest {
    @NotNull
    private String name;
    @NotNull
    private String identityKey;
    @NotNull
    private String password;
    @NotNull
    private String role;
    @NotNull
    private String status;
    @NotNull
    private Integer clearanceLevel;

    @NotNull
    private String emailId;

    public UserRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityKey() {
        return identityKey;
    }

    public void setIdentityKey(String identityKey) {
        this.identityKey = identityKey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getClearanceLevel() {
        return clearanceLevel;
    }

    public void setClearanceLevel(Integer clearanceLevel) {
        this.clearanceLevel = clearanceLevel;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}