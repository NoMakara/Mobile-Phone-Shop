package com.project.mobile_phone_shop.Exception;

import com.project.mobile_phone_shop.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalNotFoundHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<?> brandNotFoundHandler(NotFoundException exception){
      ApiResponse apiResponse = new ApiResponse(exception.getMessage(),HttpStatus.NOT_FOUND);
      return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
  }

    @ExceptionHandler(ApiResponse.class)
    public ResponseEntity<?> ApiResponseHandler(ApiResponse e){
        return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
    }

  @ExceptionHandler(AlreadyExistException.class)
  public ResponseEntity<?> brandAlreadyExistHandler(AlreadyExistException exception){
      ApiResponse apiResponse = new ApiResponse(exception.getMessage(),HttpStatus.FORBIDDEN);
      return new ResponseEntity<>(apiResponse,HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(CannotBeNullException.class)
  public ResponseEntity<?> handleCannotBeNull(CannotBeNullException exception){
      ApiResponse apiResponse = new ApiResponse(exception.getMessage(),HttpStatus.BAD_REQUEST);
      return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<?> handleProductNotValidConstraint(ConstraintViolationException exception){
      ApiResponse apiResponse = new ApiResponse(exception.getMessage(),HttpStatus.BAD_REQUEST);
      return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
  }
}

