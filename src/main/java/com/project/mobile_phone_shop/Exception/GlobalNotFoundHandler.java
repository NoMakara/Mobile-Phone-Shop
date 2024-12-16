package com.project.mobile_phone_shop.Exception;

import com.project.mobile_phone_shop.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalNotFoundHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<?> brandNotFoundHandler(NotFoundException exception){
      ApiResponse apiResponse = new ApiResponse(exception.getMessage(),HttpStatus.NOT_FOUND.value());
      return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(AlreadyExistException.class)
  public ResponseEntity<?> brandAlreadyExistHandler(AlreadyExistException exception){
      ApiResponse apiResponse = new ApiResponse(exception.getMessage(),HttpStatus.FORBIDDEN.value());
      return new ResponseEntity<>(apiResponse,HttpStatus.FORBIDDEN);
  }
}

