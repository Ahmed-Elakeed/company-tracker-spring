package com.study.companytracker.exception;


import com.study.companytracker.dto.GenericRestResponse;
import com.study.companytracker.util.enums.ResponseMessage;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.sasl.AuthenticationException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public GenericRestResponse<?> allExceptionHandler(Exception exception) {
        return GenericRestResponse.builder()
                .responseMessage(ResponseMessage.FAIL)
                .responseCode(ResponseMessage.FAIL.getCode())
                .errorMessage(exception.getMessage())
                .build();
    }

    @ExceptionHandler(value = {AuthenticationException.class})
    public GenericRestResponse<?> allExceptionHandler(AuthenticationException exception) {
        return GenericRestResponse.builder()
                .responseMessage(ResponseMessage.AUTHENTICATION_FAILURE)
                .responseCode(ResponseMessage.AUTHENTICATION_FAILURE.getCode())
                .errorMessage(exception.getMessage())
                .build();
    }
}
