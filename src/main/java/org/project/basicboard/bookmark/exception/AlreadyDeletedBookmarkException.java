package org.project.basicboard.bookmark.exception;

import org.project.basicboard.global.error.ErrorMessage;

public class AlreadyDeletedBookmarkException extends RuntimeException {
    public AlreadyDeletedBookmarkException(String message) {
        super(message);
    }

    public AlreadyDeletedBookmarkException() {
        this(ErrorMessage.ALREADY_DELETE_BOOKMARK.getMessage());
    }
}