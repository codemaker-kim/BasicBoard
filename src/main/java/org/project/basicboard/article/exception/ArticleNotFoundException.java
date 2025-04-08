package org.project.basicboard.article.exception;

import org.project.basicboard.global.error.ErrorMessage;
import org.project.basicboard.global.error.exception.CustomException;

public class ArticleNotFoundException extends CustomException {
    public ArticleNotFoundException() {
        super(ErrorMessage.ARTICLE_NOT_FOUND);
    }
}