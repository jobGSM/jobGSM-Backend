package com.example.jobgsm.global.exception.handler;

import com.example.jobgsm.domain.user.exception.NotNullException;
import com.example.jobgsm.domain.user.exception.UserNotFoundException;
import com.example.jobgsm.global.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> UserNotFoundException (UserNotFoundException exception, HttpServletRequest request){
        log.warn("UserNotFoundException 발생!!! url:{}, trace:{}",request.getRequestURI(), exception.getStackTrace());
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(NotNullException.class)
    public ResponseEntity<ErrorResponse> NotNullException (NotNullException exception, HttpServletRequest request){
        log.warn("NotNullException 발생!!! url:{}, trace:{}",request.getRequestURI(), exception.getStackTrace());
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }
}

