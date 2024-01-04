package com.study.companytracker.util.enums;

import lombok.Getter;

@Getter
public enum ErrorMessage {

    INVALID_CREDENTIALS("Invalid Credentials, please try again"),
    INTERNAL_SERVER_ERROR("Internal server error, please contact the admin");


    private final String message;
    ErrorMessage(String message) {
        this.message=message;
    }

}
