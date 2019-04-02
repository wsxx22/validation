package com.example.validation.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiError {

    private HttpStatus status;
    private List<String> errors;

    public ApiError(HttpStatus status, String error) {
        this.status = status;
        errors = List.of(error);
    }
}