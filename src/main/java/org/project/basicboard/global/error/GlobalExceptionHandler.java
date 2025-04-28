package org.project.basicboard.global.error;

import org.project.basicboard.global.error.dto.ErrorResponse;
import org.project.basicboard.global.error.dto.ValidErrorDetails;
import org.project.basicboard.global.error.dto.ValidErrorResponse;
import org.project.basicboard.global.error.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ErrorResponse> handleInvalidData(CustomException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getStatusCode(), e.getMessage());

        return ResponseEntity.status(errorResponse.statusCode())
                .body(errorResponse);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ValidErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        List<ValidErrorDetails> errors = getValidErrorDetails(e);

        ValidErrorResponse response = new ValidErrorResponse(HttpStatus.BAD_REQUEST.value(), errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(response);
    }

    private List<ValidErrorDetails> getValidErrorDetails(MethodArgumentNotValidException e) {
        return e.getBindingResult().getFieldErrors()
                .stream()
                .map(error ->
                        new ValidErrorDetails(
                                error.getField(),
                                error.getDefaultMessage())
                )
                .toList();
    }
}