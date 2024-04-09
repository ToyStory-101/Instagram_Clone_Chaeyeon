package com.ToyStory101.instagram.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handler(CustomException e){
        HttpStatus ttp = e.getStatus();
        String msg = e.getMessage();

        return ResponseEntity.status(ttp).body(msg);
    }
}
