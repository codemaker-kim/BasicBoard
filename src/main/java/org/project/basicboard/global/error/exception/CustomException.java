package org.project.basicboard.global.error.exception;

import lombok.Getter;
import org.project.basicboard.global.error.ErrorMessage;

@Getter
public abstract class CustomException extends RuntimeException {

    private final int statusCode;

    public CustomException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.statusCode = errorMessage.getStatus().value();
    }
}
