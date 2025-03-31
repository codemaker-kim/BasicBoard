package org.project.basicboard.global.error.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.FORBIDDEN;

public abstract class AccessDeniedGroupException extends CustomException {
    public AccessDeniedGroupException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return FORBIDDEN;
    }
}