package com.project.mobile_phone_shop.Exception;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException() {
        super(ErrorMessage.ALREADY_EXIST.getErrorMessage());
    }
}
