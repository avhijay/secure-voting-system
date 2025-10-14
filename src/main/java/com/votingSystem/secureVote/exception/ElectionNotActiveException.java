package com.votingSystem.secureVote.exception;

public class ElectionNotActiveException extends RuntimeException{
    public ElectionNotActiveException(String message){
        super(message
        );
    }
}
