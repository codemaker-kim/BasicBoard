package org.project.basicboard.bookmark.exception;

import org.project.basicboard.global.error.ErrorMessage;

public class NotAuthorizeBookmarkException extends RuntimeException {
    public NotAuthorizeBookmarkException(String message) {
        super(message);
    }

    public NotAuthorizeBookmarkException() {
        this(ErrorMessage.NOT_AUTHORIZE_BOOKMARK.getMessage());
    }
}