package org.project.basicboard.likes.exception;

import org.project.basicboard.global.error.ErrorMessage;

public class LikesAlreadyExistException extends RuntimeException {
    public LikesAlreadyExistException(String message) {
        super(ErrorMessage.LIKE_ALREADY_EXIST);
    }
}
