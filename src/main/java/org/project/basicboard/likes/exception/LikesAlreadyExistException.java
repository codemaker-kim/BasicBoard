package org.project.basicboard.likes.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.CustomException;

public class LikesAlreadyExistException extends CustomException {
    public LikesAlreadyExistException() {
        super(ErrorMessage.LIKE_ALREADY_EXISTS);
    }
}
