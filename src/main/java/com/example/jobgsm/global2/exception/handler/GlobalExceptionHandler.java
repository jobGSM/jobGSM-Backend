package com.example.jobgsm.global2.exception.handler;

import com.example.jobgsm.domain.application.exception.BoardNotFoundException;
import com.example.jobgsm.global2.exception.ErrorCode;
import com.example.jobgsm.global2.exception.ErrorResponse;
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

    @ExceptionHandler(BoardNotFoundException.class)
    public ResponseEntity<ErrorResponse> UserNotFoundException(BoardNotFoundException exception, HttpServletRequest request) {
        log.warn("BoardNotFoundException 발생!!! url:{}, trace:{}", request.getRequestURI(), exception.getStackTrace());
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        log.warn("MethodArgumentNotValidException 발생!!! url:{}, trace:{}",request.getRequestURI(), e.getStackTrace());
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.NOT_NULL.getMessage(), ErrorCode.NOT_NULL.getStatus());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> methodTypeMisException(MethodArgumentTypeMismatchException e, HttpServletRequest request){
        log.warn("MethodArgumentTypeMismatchException 발생!!! url:{}, trace:{}",request.getRequestURI(), e.getStackTrace());
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.NOT_NULL.getMessage(), ErrorCode.NOT_NULL.getStatus());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
