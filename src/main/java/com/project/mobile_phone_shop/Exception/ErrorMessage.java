package com.project.mobile_phone_shop.Exception;

public enum ErrorMessage {
    PRODUCT_NOT_FOUND("Product Not Found!");

    private final String message;

    ErrorMessage(String message){
        this.message = message;
    }

    public String getErrorMessage() {
        return message;
    }
}
