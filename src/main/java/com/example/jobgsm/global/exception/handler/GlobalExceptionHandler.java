package com.example.jobgsm.global.exception.handler;

import com.example.jobgsm.domain.email.exception.AuthCodeExpiredException;
import com.example.jobgsm.domain.email.exception.ManyRequestEmailAuthException;
import com.example.jobgsm.domain.email.exception.MisMatchAuthCodeException;
import com.example.jobgsm.domain.auth.exception.ExistEmailException;
import com.example.jobgsm.domain.auth.exception.MemberNotFoundException;
import com.example.jobgsm.domain.auth.exception.PasswordNotMatch;
import com.example.jobgsm.domain.user.exception.UserNotFoundException;
import com.example.jobgsm.global.exception.ErrorCode;
import com.example.jobgsm.global.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> UserNotFoundException(UserNotFoundException exception, HttpServletRequest request) {
        log.warn("UserNotFoundException 발생!!! url:{}, trace:{}", request.getRequestURI(), exception.getStackTrace());
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.warn("MethodArgumentNotValidException 발생!!! url:{}, trace:{}", request.getRequestURI(), e.getStackTrace());
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.NOT_NULL.getMessage(), ErrorCode.NOT_NULL.getStatus());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> methodTypeMisException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        log.warn("MethodArgumentTypeMismatchException 발생!!! url:{}, trace:{}", request.getRequestURI(), e.getStackTrace());
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.NOT_NULL.getMessage(), ErrorCode.NOT_NULL.getStatus());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMemberNotFoundException(HttpServletRequest request , MemberNotFoundException e) {
        log.warn("handleMemberNotFoundException 발생!!! url:{}, trace:{}", request.getRequestURI(), e.getStackTrace());
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.NOT_NULL.getMessage(), ErrorCode.NOT_NULL.getStatus());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordNotMatch.class)
    public ResponseEntity<ErrorResponse> handleMisMatchPasswordException(HttpServletRequest request , PasswordNotMatch e) {
        log.warn("handleMisMatchPasswordException 발생!!! url:{}, trace:{}", request.getRequestURI(), e.getStackTrace());
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.NOT_NULL.getMessage(), ErrorCode.NOT_NULL.getStatus());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ExistEmailException.class)
    public ResponseEntity<ErrorResponse> handleExistEmailException(HttpServletRequest request , ExistEmailException e) {
        log.warn("handleExistEmailException 발생!!! url:{}, trace:{}", request.getRequestURI(), e.getStackTrace());
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.NOT_NULL.getMessage(), ErrorCode.NOT_NULL.getStatus());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AuthCodeExpiredException.class)
    public ResponseEntity<ErrorResponse> handleAuthCodeExpiredException(HttpServletRequest request , AuthCodeExpiredException e) {
        log.warn("handleAuthCodeExpiredException 발생!!! url:{}, trace:{}", request.getRequestURI(), e.getStackTrace());
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.NOT_NULL.getMessage(), ErrorCode.NOT_NULL.getStatus());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ManyRequestEmailAuthException.class)
    public ResponseEntity<ErrorResponse> handleManyRequestEmailAuthException(HttpServletRequest request , ManyRequestEmailAuthException e) {
        log.warn("handleManyRequestEmailAuthException 발생!!! url:{}, trace:{}", request.getRequestURI(), e.getStackTrace());
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.NOT_NULL.getMessage(), ErrorCode.NOT_NULL.getStatus());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MisMatchAuthCodeException.class)
    public ResponseEntity<ErrorResponse> handleMisMatchAuthCodeException(HttpServletRequest request , MisMatchAuthCodeException e) {
        log.warn("handleMisMatchAuthCodeException 발생!!! url:{}, trace:{}", request.getRequestURI(), e.getStackTrace());
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.NOT_NULL.getMessage(), ErrorCode.NOT_NULL.getStatus());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(com.example.jobgsm.domain.email.exception.MemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMemberNotFoundException(HttpServletRequest request , com.example.jobgsm.domain.email.exception.MemberNotFoundException e) {
        log.warn("handleMemberNotFoundException 발생!!! url:{}, trace:{}", request.getRequestURI(), e.getStackTrace());
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.NOT_NULL.getMessage(), ErrorCode.NOT_NULL.getStatus());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}

