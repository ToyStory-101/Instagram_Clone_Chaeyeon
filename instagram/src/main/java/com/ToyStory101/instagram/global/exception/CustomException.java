package com.ToyStory101.instagram.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@Getter
public class CustomException extends RuntimeException{
    private final String message;
    private final HttpStatus status;

    public CustomException(HttpStatus status, String message){
        this.message = message;
        this.status = status;
    }
}
