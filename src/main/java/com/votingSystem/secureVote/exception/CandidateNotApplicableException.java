package com.votingSystem.secureVote.exception;

public class CandidateNotApplicableException extends  RuntimeException{
    public CandidateNotApplicableException(String message){
        super(message);
    }
}
