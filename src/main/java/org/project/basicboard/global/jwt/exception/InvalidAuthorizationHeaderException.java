package org.project.basicboard.global.jwt.exception;

import org.project.basicboard.global.error.exception.CustomException;

import static org.project.basicboard.global.error.ErrorMessage.INVALID_HEADER;

public class InvalidAuthorizationHeaderException extends CustomException {
    public InvalidAuthorizationHeaderException() {
        super(INVALID_HEADER);
    }
}
