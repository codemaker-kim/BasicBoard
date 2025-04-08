package org.project.basicboard.global.jwt.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.CustomException;

public class RefreshTokenNotFoundException extends CustomException {
    public RefreshTokenNotFoundException() {
        super(ErrorMessage.REFRESH_TOKEN_NOT_FOUND);
    }
}