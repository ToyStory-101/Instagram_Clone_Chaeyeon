package com.ToyStory101.instagram.global.exception;

import com.ToyStory101.instagram.global.dto.CODE;
import com.ToyStory101.instagram.global.dto.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handler(CustomException e){
        HttpStatus ttp = e.getStatus();
        Result result = new Result();
        result.setData(null);
        result.setCode(CODE.FAIL);
        result.setMessage(e.getMessage());

        return ResponseEntity.status(ttp).body(result);
    }
}
