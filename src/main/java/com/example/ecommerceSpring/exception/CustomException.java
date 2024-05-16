package com.example.ecommerceSpring.exception;

import com.example.ecommerceSpring.dtos.ExceptionDto;
import org.springframework.http.HttpStatusCode;


// custom exception class
public class CustomException extends RuntimeException {
    // add an error code
    private final HttpStatusCode errorCode;

    public CustomException(String message, HttpStatusCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorValueCode() {
        return errorCode.value();
    }

    public HttpStatusCode getErrorCode() {
        return this.errorCode;
    }

    public ExceptionDto toExceptionDto() {
        return new ExceptionDto(errorCode.value(), this.getMessage());
    }
}
