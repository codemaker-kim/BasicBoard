package org.project.basicboard.article.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.CustomException;

public class TitleLengthException extends CustomException {

    public TitleLengthException() {
        super(ErrorMessage.LONG_TITLE_LENGTH);
    }
}