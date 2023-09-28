package com.study.companytracker.util.enums;

public enum ResponseMessage {

    SUCCESS(200L),
    FAIL(500L);


    private Long code;

    ResponseMessage(Long code) {
        this.code=code;
    }

    public Long getCode() {
        return code;
    }
}
