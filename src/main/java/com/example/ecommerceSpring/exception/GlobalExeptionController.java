package com.example.ecommerceSpring.exception;

import com.example.ecommerceSpring.dtos.ExceptionDto;
import com.example.ecommerceSpring.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExeptionController {
    @ExceptionHandler(value = {CustomException.class})
    protected ResponseEntity customExpectionHandler(CustomException ex, WebRequest request) {
        final List<ExceptionDto> errors = new ArrayList<>();
        errors.add(ex.toExceptionDto());


        return new ResponseEntity(new ApiResponse(false, new ArrayList<>(Collections.singleton(ex.toExceptionDto())), null), ex.getErrorCode());
    }
}