package com.study.companytracker.util.enums;

import lombok.Getter;

@Getter
public enum ResponseMessage {

    SUCCESS(200L),
    FAIL(500L),
    AUTHENTICATION_FAILURE(300L);


    private final Long code;

    ResponseMessage(Long code) {
        this.code=code;
    }

}
