package com.project.mobile_phone_shop.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalNotFoundException {

  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<?> productNotFoundHandler(ProductNotFoundException exception){
      return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
  }
}

