package org.project.basicboard.comment.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.NotFoundGroupException;

public class CommentNotFoundException extends NotFoundGroupException {
    public CommentNotFoundException(String message) {
        super(message);
    }

    public CommentNotFoundException() {
        this(ErrorMessage.COMMENT_NOT_FOUND.getMessage());
    }
}
