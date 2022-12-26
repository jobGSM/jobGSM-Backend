package com.example.jobgsm.global.exception.handler;

import com.example.jobgsm.domain.auth.exception.*;
import com.example.jobgsm.domain.email.exception.AuthCodeExpiredException;
import com.example.jobgsm.domain.email.exception.AuthCodeMismatchException;
import com.example.jobgsm.domain.email.exception.ManyRequestEmailAuthException;
import com.example.jobgsm.domain.user.exception.PasswordWrongException;
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
    // 유저를 찾을 수 없습니다
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException exception, HttpServletRequest request) {
        log.warn("UserNotFoundException 발생!!! url:{}, trace:{}", request.getRequestURI(), exception.getStackTrace());
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }
    @ExceptionHandler(NotVerifyEmailException.class)
    public ResponseEntity<ErrorResponse> handleNotVerifyEmail(NotVerifyEmailException exception, HttpServletRequest request) {
        log.warn("NotVerifyEmailException 발생!!! url:{}, trace:{}", request.getRequestURI(), exception.getStackTrace());
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    // 비밀번호가 일치하지 않습니다
    @ExceptionHandler(PasswordWrongException.class)
    public ResponseEntity<ErrorResponse> handlePasswordWrongException( PasswordWrongException exception,HttpServletRequest request) {
        log.warn("handlePasswordWrongException 발생!!! url:{}, trace:{}", request.getRequestURI(), exception.getStackTrace());
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.NOT_NULL.getMessage(), ErrorCode.NOT_NULL.getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }
    @ExceptionHandler(BlackListAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleBlackListAlreadyExistException(BlackListAlreadyExistException exception,HttpServletRequest request) {
        log.warn("handleMisMatchPasswordException 발생!!! url:{}, trace:{}", request.getRequestURI(), exception.getStackTrace());
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.NOT_NULL.getMessage(), ErrorCode.NOT_NULL.getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    // 이미 존재하는 이메일 입니다
    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleExistEmailException(EmailAlreadyExistException exception,HttpServletRequest request) {
        log.warn("handleExistEmailException 발생!!! url:{}, trace:{}", request.getRequestURI(), exception.getStackTrace());
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.NOT_NULL.getMessage(), ErrorCode.NOT_NULL.getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(AuthCodeExpiredException.class)
    public ResponseEntity<ErrorResponse> handleAuthCodeExpiredException(AuthCodeExpiredException exception, HttpServletRequest request) {
        log.warn("handleAuthCodeExpiredException 발생!!! url:{}, trace:{}", request.getRequestURI(), exception.getStackTrace());
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.NOT_NULL.getMessage(), ErrorCode.NOT_NULL.getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(ManyRequestEmailAuthException.class)
    public ResponseEntity<ErrorResponse> handleManyRequestEmailAuthException(HttpServletRequest request , ManyRequestEmailAuthException exception) {
        log.warn("handleManyRequestEmailAuthException 발생!!! url:{}, trace:{}", request.getRequestURI(), exception.getStackTrace());
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.NOT_NULL.getMessage(), ErrorCode.NOT_NULL.getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(AuthCodeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleAuthCodeMismatchException(HttpServletRequest request , AuthCodeMismatchException exception) {
        log.warn("handleAuthCodeMismatchException 발생!!! url:{}, trace:{}", request.getRequestURI(), exception.getStackTrace());
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.NOT_NULL.getMessage(), ErrorCode.NOT_NULL.getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(RefreshTokenNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRefreshTokenNotFoundException(HttpServletRequest request , RefreshTokenNotFoundException exception) {
        log.warn("handleRefreshTokenNotFoundException 발생!!! url:{}, trace:{}", request.getRequestURI(), exception.getStackTrace());
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.NOT_NULL.getMessage(), ErrorCode.NOT_NULL.getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }


}

