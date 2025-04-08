package org.project.basicboard.global.security.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.CustomException;

public class NoAuthenticationInfoException extends CustomException {
    public NoAuthenticationInfoException() {
        super(ErrorMessage.NO_AUTHENTICATION_INFO);
    }
}