package org.project.basicboard.auth.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.AccessDeniedGroupException;

public class NotAuthorizeUpdateNicknameException extends AccessDeniedGroupException {

    public NotAuthorizeUpdateNicknameException(String message) {
        super(message);
    }

    public NotAuthorizeUpdateNicknameException() {
        this(ErrorMessage.NOT_AUTHORIZE_NICKNAME_UPDATE.getMessage());
    }
}
