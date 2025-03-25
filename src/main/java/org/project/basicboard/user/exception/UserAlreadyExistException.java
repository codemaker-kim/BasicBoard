package org.project.basicboard.user.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.InvalidGroupException;

public class UserAlreadyExistException extends InvalidGroupException {
    public UserAlreadyExistException(String message) {
        super(message);
    }

    public UserAlreadyExistException() {
        this(ErrorMessage.USER_ALREADY_EXISTS.getMessage());
    }
}