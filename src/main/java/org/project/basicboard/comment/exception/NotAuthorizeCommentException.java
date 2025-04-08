package org.project.basicboard.comment.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.CustomException;

public class NotAuthorizeCommentException extends CustomException {
    public NotAuthorizeCommentException() {
        super(ErrorMessage.NOT_AUTHORIZE_COMMENT);
    }
}
