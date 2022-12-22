package com.example.jobgsm.domain.email.global.exception.handler;

import com.example.jobgsm.domain.email.domain.exception.AuthCodeExpiredException;
import com.example.jobgsm.domain.email.domain.exception.ManyRequestEmailAuthException;
import com.example.jobgsm.domain.email.domain.exception.MemberNotFoundException;
import com.example.jobgsm.domain.email.domain.exception.MisMatchAuthCodeException;
import com.example.jobgsm.domain.email.global.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthCodeExpiredException.class)
    public ResponseEntity<ErrorResponse> handleAuthCodeExpiredException(HttpServletRequest request , AuthCodeExpiredException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(ManyRequestEmailAuthException.class)
    public ResponseEntity<ErrorResponse> handleManyRequestEmailAuthException(HttpServletRequest request , ManyRequestEmailAuthException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(MisMatchAuthCodeException.class)
    public ResponseEntity<ErrorResponse> handleMisMatchAuthCodeException(HttpServletRequest request , MisMatchAuthCodeException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMemberNotFoundException(HttpServletRequest request , MemberNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

   }