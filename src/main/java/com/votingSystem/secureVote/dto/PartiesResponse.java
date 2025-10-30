package com.votingSystem.secureVote.dto;

import jakarta.persistence.Column;

import java.sql.Timestamp;

public class PartiesResponse {



    private String name;


    private String symbol;


    private String description;

    private Integer foundedYear;

    private Timestamp createdAt;



    public PartiesResponse(){}


    public PartiesResponse(Timestamp createdAt, Integer foundedYear, String description, String symbol, String name) {
        this.createdAt = createdAt;
        this.foundedYear = foundedYear;
        this.description = description;
        this.symbol = symbol;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFoundedYear() {
        return foundedYear;
    }

    public void setFoundedYear(Integer foundedYear) {
        this.foundedYear = foundedYear;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
