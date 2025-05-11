package org.project.basicboard.bookmark.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.CustomException;

public class BookmarkAlreadyExistException extends CustomException {
    public BookmarkAlreadyExistException() {
        super(ErrorMessage.BOOKMARK_ALREADY_EXIST);
    }
}
