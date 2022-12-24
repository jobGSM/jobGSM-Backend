package com.example.jobgsm.global3.exception.handler;

import com.example.jobgsm.domain3.signup.exception.ExistEmailException;
import com.example.jobgsm.domain3.signup.exception.MemberNotFoundException;
import com.example.jobgsm.domain3.signup.exception.PasswordNotMatch;
import com.example.jobgsm.global3.exception.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;


@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleMemberNotFoundException(HttpServletRequest request , MemberNotFoundException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(PasswordNotMatch.class)
    public ResponseEntity<ErrorMessage> handleMisMatchPasswordException(HttpServletRequest request , PasswordNotMatch e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }


    @ExceptionHandler(ExistEmailException.class)
    public ResponseEntity<ErrorMessage> handleExistEmailException(HttpServletRequest request , ExistEmailException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    private void printError(HttpServletRequest request, RuntimeException ex, String message) {
        log.error(request.getRequestURI());
        log.error(message);
        ex.printStackTrace();
    }
}
