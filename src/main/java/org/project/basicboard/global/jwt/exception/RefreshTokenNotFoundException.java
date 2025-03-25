package org.project.basicboard.global.jwt.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.NotFoundGroupException;

public class RefreshTokenNotFoundException extends NotFoundGroupException {
    public RefreshTokenNotFoundException(String message) {
        super(message);
    }

    public RefreshTokenNotFoundException() {
      this(ErrorMessage.REFRESH_TOKEN_NOT_FOUND.getMessage());
    }
}
