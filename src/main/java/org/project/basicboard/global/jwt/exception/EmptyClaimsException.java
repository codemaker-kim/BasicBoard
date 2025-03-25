package org.project.basicboard.global.jwt.exception;

import org.project.basicboard.global.error.exception.AuthGroupException;

import static org.project.basicboard.global.error.ErrorMessage.EMPTY_CLAIMS;

public class EmptyClaimsException extends AuthGroupException {

    public EmptyClaimsException(String message) {
        super(message);
    }

    public EmptyClaimsException() {
        this(EMPTY_CLAIMS.getMessage());
    }
}
