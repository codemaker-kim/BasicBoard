package org.project.basicboard.global.error.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public abstract class NotFoundGroupException extends CustomException {
    public NotFoundGroupException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return NOT_FOUND;
    }
}