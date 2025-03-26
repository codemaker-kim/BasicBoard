package org.project.basicboard.article.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.AccessDeniedGroupException;

public class NotAuthorizeArticleException extends AccessDeniedGroupException {
    public NotAuthorizeArticleException(String message) {
        super(message);
    }

    public NotAuthorizeArticleException() {
        this(ErrorMessage.NOT_AUTHORIZE_ARTICLE.getMessage());
    }
}
