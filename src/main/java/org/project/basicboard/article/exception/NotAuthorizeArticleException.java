package org.project.basicboard.article.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.CustomException;

public class NotAuthorizeArticleException extends CustomException {
    public NotAuthorizeArticleException() {
        super(ErrorMessage.NOT_AUTHORIZE_ARTICLE);
    }
}