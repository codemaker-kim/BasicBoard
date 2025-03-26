package org.project.basicboard.article.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.AccessDeniedGroupException;

public class NotAuthorizeDeleteArticleException extends AccessDeniedGroupException {
    public NotAuthorizeDeleteArticleException(String message) {
        super(message);
    }

    public NotAuthorizeDeleteArticleException() {
        this(ErrorMessage.NOT_AUTHORIZE_ARTICLE_DELETE.getMessage());
    }
}
