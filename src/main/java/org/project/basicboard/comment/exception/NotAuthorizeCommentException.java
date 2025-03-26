package org.project.basicboard.comment.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.AccessDeniedGroupException;

public class NotAuthorizeCommentException extends AccessDeniedGroupException {
    public NotAuthorizeCommentException(String message) {
        super(message);
    }

    public NotAuthorizeCommentException() {
        this(ErrorMessage.NOT_AUTHORIZE_COMMENT.getMessage());
    }
}
