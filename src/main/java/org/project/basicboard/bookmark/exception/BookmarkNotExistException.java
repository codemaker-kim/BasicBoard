package org.project.basicboard.bookmark.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.CustomException;

public class BookmarkNotExistException extends CustomException {
    public BookmarkNotExistException() {
        super(ErrorMessage.BOOKMARK_NOT_EXIST);
    }
}
