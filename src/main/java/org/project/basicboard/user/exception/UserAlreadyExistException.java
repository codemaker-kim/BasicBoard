package org.project.basicboard.user.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.CustomException;

public class UserAlreadyExistException extends CustomException {
    public UserAlreadyExistException() {
        super(ErrorMessage.USER_ALREADY_EXISTS);
    }
}