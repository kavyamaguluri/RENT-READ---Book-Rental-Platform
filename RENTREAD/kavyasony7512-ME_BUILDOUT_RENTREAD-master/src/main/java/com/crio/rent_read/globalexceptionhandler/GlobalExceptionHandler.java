package com.crio.rent_read.globalexceptionhandler;

import com.crio.rent_read.exceptions.RentalLimitExceededException;
import com.crio.rent_read.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex){
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);  
    }


    @ExceptionHandler(RentalLimitExceededException.class)
    public ResponseEntity<String> handleRentalLimitExceeded(RentalLimitExceededException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
}
