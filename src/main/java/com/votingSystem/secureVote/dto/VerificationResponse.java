package com.votingSystem.secureVote.dto;

import com.votingSystem.secureVote.entity.Users;
import com.votingSystem.secureVote.entity.Votes;

import java.sql.Timestamp;

public class VerificationResponse {


    private Votes vote;


    private Users user;


    private String method;


    private String status;


    private Timestamp capturedAt;


    private String fileLocation;



    public VerificationResponse(){}

    public VerificationResponse(Votes vote, Users user, String method, String status, Timestamp capturedAt, String fileLocation) {
        this.vote = vote;
        this.user = user;
        this.method = method;
        this.status = status;
        this.capturedAt = capturedAt;
        this.fileLocation = fileLocation;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public Timestamp getCapturedAt() {
        return capturedAt;
    }

    public void setCapturedAt(Timestamp capturedAt) {
        this.capturedAt = capturedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Votes getVote() {
        return vote;
    }

    public void setVote(Votes vote) {
        this.vote = vote;
    }
}
