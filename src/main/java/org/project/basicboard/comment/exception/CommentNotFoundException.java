package org.project.basicboard.comment.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.CustomException;

public class CommentNotFoundException extends CustomException {
    public CommentNotFoundException() {
        super(ErrorMessage.COMMENT_NOT_FOUND);
    }
}
