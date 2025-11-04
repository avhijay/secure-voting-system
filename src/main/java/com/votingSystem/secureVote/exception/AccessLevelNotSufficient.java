package com.votingSystem.secureVote.exception;

public class AccessLevelNotSufficient extends  RuntimeException{


    public AccessLevelNotSufficient(String message){
    super(message);
    }

}
