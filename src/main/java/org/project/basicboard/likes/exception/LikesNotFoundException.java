package org.project.basicboard.likes.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.CustomException;

public class LikesNotFoundException extends CustomException {
    public LikesNotFoundException() {
        super(ErrorMessage.LIKE_NOT_FOUND);
    }
}