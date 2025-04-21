package com.crio.rent_read.exceptions;

public class RentalLimitExceededException extends RuntimeException {
    public RentalLimitExceededException(String message){
        super(message);
    }
    
}
