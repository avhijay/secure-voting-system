package com.votingSystem.secureVote.dto;

import jakarta.persistence.Column;

import java.sql.Timestamp;

public class PartiesRequest {



    private String name;


    private String symbol;


    private String description;


    private Integer foundedYear;




    public  PartiesRequest(){}


    public PartiesRequest(String name, String symbol, String description, Integer foundedYear) {
        this.name = name;
        this.symbol = symbol;
        this.description = description;
        this.foundedYear = foundedYear;
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
}
