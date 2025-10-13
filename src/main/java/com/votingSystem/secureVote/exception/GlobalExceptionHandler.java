package com.votingSystem.secureVote.exception;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
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
        error.setMessage("Invalid entry");
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimeStamp("Time: "+Timestamp.valueOf(LocalDateTime.now()));

        return  new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);

    }
}



