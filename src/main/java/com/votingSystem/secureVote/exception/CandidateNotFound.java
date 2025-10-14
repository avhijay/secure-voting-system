package com.votingSystem.secureVote.exception;

public class CandidateNotFound extends  RuntimeException{
    public CandidateNotFound(String message){
        super(message);
    }
}
