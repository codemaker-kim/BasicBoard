package org.project.basicboard.user.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.CustomException;

public class LogoutUserNotFoundException extends CustomException {
    public LogoutUserNotFoundException() {
        super(ErrorMessage.LOGOUT_USER_NOT_FOUND);
    }
}