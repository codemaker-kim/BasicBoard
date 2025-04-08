package org.project.basicboard.auth.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.CustomException;

public class NotAuthorizeUpdateNicknameException extends CustomException {
    public NotAuthorizeUpdateNicknameException() {
        super(ErrorMessage.NOT_AUTHORIZE_NICKNAME_UPDATE);
    }
}