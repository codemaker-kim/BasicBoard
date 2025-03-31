package org.project.basicboard.global.error;

import lombok.extern.slf4j.Slf4j;
import org.project.basicboard.global.error.dto.ErrorResponse;
import org.project.basicboard.global.error.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ErrorResponse> handleInvalidData(CustomException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getStatus().value(), e.getMessage());
        log.error(e.getMessage());

        return new ResponseEntity<>(errorResponse, e.getStatus());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        log.error(e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}