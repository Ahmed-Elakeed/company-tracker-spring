package com.study.companytracker.exception;


import com.study.companytracker.dto.GenericRestResponse;
import com.study.companytracker.util.enums.ResponseMessage;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public GenericRestResponse<?> notFoundExceptionHandler(NotFoundException exception){
        return GenericRestResponse.builder()
                .responseMessage(ResponseMessage.FAIL)
                .responseCode(ResponseMessage.FAIL.getCode())
                .errorMessage(exception.getMessage())
                .build();
    }
}
