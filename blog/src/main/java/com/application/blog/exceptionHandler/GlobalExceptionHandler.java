package com.application.blog.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> globalExceptionHandler(Exception exp, WebRequest req){


        MyErrorDetails err= new MyErrorDetails();
        err.setTime(LocalDateTime.now());
        err.setMessage(exp.getMessage());
        err.setDetails(req.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

    }

}
