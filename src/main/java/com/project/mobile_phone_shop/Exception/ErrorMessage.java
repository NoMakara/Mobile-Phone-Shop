package com.project.mobile_phone_shop.Exception;

public enum ErrorMessage {
    NOT_FOUND("Not Found!"),
    ALREADY_EXIST("Already Exist!"),
    CANNOT_BE_NULL("Value cannot be null");

    private final String message;

    ErrorMessage(String message){
        this.message = message;
    }


    public String getErrorMessage() {
        return message;
    }
}

