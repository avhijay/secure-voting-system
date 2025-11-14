package com.votingSystem.secureVote.dto;

import com.votingSystem.secureVote.entity.Users;
import jakarta.validation.constraints.NotNull;

public class UserResponse {


private Long id;
    private String name;

    private String role;

    private String status;

    private Integer clearanceLevel;

    private String emailId;

    public UserResponse(Users saved) {}
    public UserResponse(){}

    public UserResponse(Long id, String name, String role, String status, Integer clearanceLevel, String emailId) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.status = status;
        this.clearanceLevel = clearanceLevel;
        this.emailId = emailId;
    }

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

    public Long getId() {
        return id;
    }


}
