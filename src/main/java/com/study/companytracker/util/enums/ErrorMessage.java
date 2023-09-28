package com.study.companytracker.util.enums;

public enum ErrorMessage {

    INVALID_CREDENTIALS("Invalid Credentials, please try again"),
    INTERNAL_SERVER_ERROR("Internal server error, please contact the admin");


    private String message;
    ErrorMessage(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }
}
