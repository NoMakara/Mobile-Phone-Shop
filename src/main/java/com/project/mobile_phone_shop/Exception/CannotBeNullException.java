package com.project.mobile_phone_shop.Exception;

public class CannotBeNullException extends RuntimeException {
    public CannotBeNullException() {
        super(ErrorMessage.CANNOT_BE_NULL.getErrorMessage());
    }
}
