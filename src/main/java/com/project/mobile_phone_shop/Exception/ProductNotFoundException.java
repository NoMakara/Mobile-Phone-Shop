package com.project.mobile_phone_shop.Exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super(ErrorMessage.PRODUCT_NOT_FOUND.getErrorMessage());
    }
}
