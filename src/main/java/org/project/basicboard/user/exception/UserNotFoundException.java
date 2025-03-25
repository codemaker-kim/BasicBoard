package org.project.basicboard.user.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.NotFoundGroupException;

public class UserNotFoundException extends NotFoundGroupException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        this(ErrorMessage.USER_NOT_FOUND.getMessage());
    }
}