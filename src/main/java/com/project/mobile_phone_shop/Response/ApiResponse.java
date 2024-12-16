package com.project.mobile_phone_shop.Response;
import lombok.Data;

@Data
public class ApiResponse {
    private String message;
    private int statusCode;

    public ApiResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
