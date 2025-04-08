package org.project.basicboard.user.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.CustomException;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException() {
        super(ErrorMessage.USER_NOT_FOUND);
    }
}