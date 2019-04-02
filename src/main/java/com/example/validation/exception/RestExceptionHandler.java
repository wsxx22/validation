package com.example.validation.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getDefaultMessage())
                .collect(Collectors.toList());

        errors.addAll(ex.getBindingResult().getGlobalErrors().stream()
                .map(e -> e.getDefaultMessage())
                .collect(Collectors.toList()));

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, errors);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

//    @ExceptionHandler({ ConstraintViolationException.class })
//    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
//        logger.info(ex.getClass().getName());
//
//        List<String> errors = ex.getConstraintViolations().stream()
//                .map(ConstraintViolation::getMessage)
//                .collect(Collectors.toList());
//
//        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, errors);
//        return
//    }

    @ExceptionHandler({ UserNotFoundException.class })
    public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex) {
        logger.info(ex.getClass().getName());

        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
