package org.project.basicboard.user.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.InvalidGroupException;

public class AlreadyExistNicknameException extends InvalidGroupException {
    public AlreadyExistNicknameException(String message) {
        super(message);
    }

    public AlreadyExistNicknameException() {
        this(ErrorMessage.NICKNAME_ALREADY_EXISTS.getMessage());
    }
}
