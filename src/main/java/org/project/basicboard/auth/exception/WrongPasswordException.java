package org.project.basicboard.auth.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.AuthGroupException;

public class WrongPasswordException extends AuthGroupException {
    public WrongPasswordException(String message) {
        super(message);
    }

    public WrongPasswordException() {
      this(ErrorMessage.WRONG_PASSWORD.getMessage());
    }
}
