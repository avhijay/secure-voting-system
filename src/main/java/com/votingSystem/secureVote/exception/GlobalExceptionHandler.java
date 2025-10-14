package com.votingSystem.secureVote.exception;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestControllerAdvice

public class GlobalExceptionHandler {




    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllException(Exception e, HttpServletRequest request
                                                            ){
        ErrorResponse error = new ErrorResponse();
        error.setError("Check logs : handled through - handleAllExceptions");
        error.setPath(request.getRequestURI());
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimeStamp("Time: "+Timestamp.valueOf(LocalDateTime.now()));

        return  new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(DuplicateVoteException.class)

    public ResponseEntity<ErrorResponse>DuplicateVoteException(DuplicateVoteException e , HttpServletRequest request){
        ErrorResponse error = new ErrorResponse();
        error.setTimeStamp("Time :"+Timestamp.valueOf(LocalDateTime.now()));
        error.setError("Duplicate vote practice not allowed");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error,HttpStatus.FORBIDDEN);

    }

    @ExceptionHandler(ElectionNotActiveException.class)
    public ResponseEntity<ErrorResponse>ElectionNotFound(ElectionNotActiveException e , HttpServletRequest request){
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setPath(request.getRequestURI());
        error.setMessage(e.getMessage());
        error.setTimeStamp("Time: "+Timestamp.valueOf(LocalDateTime.now()));
        error.setError("Election not found at the location");

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CandidateNotApplicableException.class)
    public ResponseEntity<ErrorResponse>CandidateNotApplicable(CandidateNotApplicableException e , HttpServletRequest request){
        ErrorResponse error = new ErrorResponse();
        error.setPath(request.getRequestURI());
        error.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        error.setMessage(e.getMessage());
        error.setTimeStamp("Time :"+Timestamp.valueOf(LocalDateTime.now()));
        error.setError("Candidate Approval pending or denied");

        return new  ResponseEntity<>(error,HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse>ResourceNotFound(ResourceNotFoundException e ,HttpServletRequest request){
        ErrorResponse error = new ErrorResponse();

        error.setPath(request.getRequestURI());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(e.getMessage());
        error.setTimeStamp("Time :"+Timestamp.valueOf(LocalDateTime.now()));
        error.setError("Resource not found");

        return new  ResponseEntity<>(error,HttpStatus.SERVICE_UNAVAILABLE);

    }

}



