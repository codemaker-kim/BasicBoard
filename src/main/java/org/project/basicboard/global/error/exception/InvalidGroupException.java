package org.project.basicboard.global.error.exception;

public abstract class InvalidGroupException extends RuntimeException {
    public InvalidGroupException(String message) {
        super(message);
    }
}