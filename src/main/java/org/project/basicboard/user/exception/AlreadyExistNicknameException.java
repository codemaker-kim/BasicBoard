package org.project.basicboard.user.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.CustomException;

public class AlreadyExistNicknameException extends CustomException {
    public AlreadyExistNicknameException() {
        super(ErrorMessage.NICKNAME_ALREADY_EXISTS);
    }
}