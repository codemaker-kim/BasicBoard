package org.project.basicboard.article.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.NotFoundGroupException;

public class ArticleNotFoundException extends NotFoundGroupException {
    public ArticleNotFoundException(String message) {
        super(message);
    }

    public ArticleNotFoundException() {
        this(ErrorMessage.ARTICLE_NOT_FOUND.getMessage());
    }
}
