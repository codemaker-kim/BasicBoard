package org.project.basicboard.global.jwt.exception;

import org.project.basicboard.global.error.exception.CustomException;

import static org.project.basicboard.global.error.ErrorMessage.EMPTY_CLAIMS;

public class EmptyClaimsException extends CustomException {
    public EmptyClaimsException() {
        super(EMPTY_CLAIMS);
    }
}