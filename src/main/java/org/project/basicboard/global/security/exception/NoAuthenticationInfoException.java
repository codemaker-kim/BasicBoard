package org.project.basicboard.global.security.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.NotFoundGroupException;

public class NoAuthenticationInfoException extends NotFoundGroupException {
    public NoAuthenticationInfoException(String message) {
        super(message);
    }

    public NoAuthenticationInfoException() {
        this(ErrorMessage.NO_AUTHENTICATION_INFO.getMessage());
    }
}