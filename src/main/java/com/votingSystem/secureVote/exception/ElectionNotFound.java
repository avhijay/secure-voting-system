package com.votingSystem.secureVote.exception;

public class ElectionNotFound extends RuntimeException{
    public ElectionNotFound(String message){
        super(message);
    }
}
