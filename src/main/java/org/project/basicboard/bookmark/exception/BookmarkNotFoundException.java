package org.project.basicboard.bookmark.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.NotFoundGroupException;

public class BookmarkNotFoundException extends NotFoundGroupException {
    public BookmarkNotFoundException(String message) {
        super(message);
    }

    public BookmarkNotFoundException() {
        this(ErrorMessage.BOOKMARK_NOT_FOUND.getMessage());
    }
}