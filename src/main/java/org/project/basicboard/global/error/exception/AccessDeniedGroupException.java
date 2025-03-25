package org.project.basicboard.global.error.exception;

public abstract class AccessDeniedGroupException extends RuntimeException {
    public AccessDeniedGroupException(String message) {
        super(message);
    }
}
