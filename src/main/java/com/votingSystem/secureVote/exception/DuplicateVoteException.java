package com.votingSystem.secureVote.exception;

public class DuplicateVoteException extends RuntimeException{
    public DuplicateVoteException(String message){
        super(message);
    }
}
