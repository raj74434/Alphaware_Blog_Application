package com.application.blog.exceptionHandler;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MyErrorDetails {

    private LocalDateTime time;

    private String Details;
    private String message;

}
