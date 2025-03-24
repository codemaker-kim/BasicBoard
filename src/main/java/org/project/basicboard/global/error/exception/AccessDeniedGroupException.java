package org.project.basicboard.global.error.exception;

public abstract class AccessDeniedGroupException extends Throwable {
    public AccessDeniedGroupException(String message) {
        super(message);
    }
}
