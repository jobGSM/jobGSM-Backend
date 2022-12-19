package com.example.jobgsm.global.exception.handler;

import com.example.jobgsm.domain.user.exception.NotNullException;
import com.example.jobgsm.domain.user.exception.UserNotFoundException;
import com.example.jobgsm.global.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> UserNotFoundException (UserNotFoundException exception){
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(NotNullException.class)
    public ResponseEntity<ErrorResponse> NotNullException (NotNullException exception){
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }
}

