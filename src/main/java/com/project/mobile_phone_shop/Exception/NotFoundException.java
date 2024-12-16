package com.project.mobile_phone_shop.Exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super(ErrorMessage.NOT_FOUND.getErrorMessage());
    }
}
