package org.project.basicboard.user.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.CustomException;

public class NoUserMatchRefreshTokenException extends CustomException {
    public NoUserMatchRefreshTokenException() {
        super(ErrorMessage.NO_USER_MATCH_REFRESH_TOKEN);
    }
}