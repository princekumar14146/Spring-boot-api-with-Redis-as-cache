package com.springBoot_Redis_cache.demo.Exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataAlreadyPresentException.class)
    public ResponseEntity<String> handleDataAlreadyPresentException(DataAlreadyPresentException ex)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NoDataPresentEXception.class)
    public ResponseEntity<String> handleNoDataPresentException(NoDataPresentEXception ex)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String,String>> handleConstraintsViolationException(ConstraintViolationException ex)
    {
        Map<String,String> errors=new HashMap<>();
        ex.getConstraintViolations().forEach((error)->{
            String fieldName=error.getPropertyPath().toString();
            String errorMessage=error.getMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.OK).body(errors);

    }

}
