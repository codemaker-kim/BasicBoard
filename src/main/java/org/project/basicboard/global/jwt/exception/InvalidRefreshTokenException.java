package org.project.basicboard.global.jwt.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.InvalidGroupException;

public class InvalidRefreshTokenException extends InvalidGroupException {
    public InvalidRefreshTokenException(String message) {
        super(message);
    }

    public InvalidRefreshTokenException() {
        this(ErrorMessage.INVALID_TOKEN.getMessage());
    }
}
