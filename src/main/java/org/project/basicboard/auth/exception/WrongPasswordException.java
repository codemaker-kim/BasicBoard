package org.project.basicboard.auth.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.CustomException;

public class WrongPasswordException extends CustomException {
    public WrongPasswordException() {
        super(ErrorMessage.WRONG_PASSWORD);
    }
}