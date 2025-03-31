package org.project.basicboard.global.error.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public abstract class AuthGroupException extends CustomException {
    public AuthGroupException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return BAD_REQUEST;
    }
}