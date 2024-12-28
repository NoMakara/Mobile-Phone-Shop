package com.project.mobile_phone_shop.Response;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponse extends RuntimeException {
    private String message;
    private HttpStatus statusCode;

    public ApiResponse(String message, HttpStatus httpStatus) {
        this.message = message;
        this.statusCode = httpStatus;
    }
}
