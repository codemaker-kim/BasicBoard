package org.project.basicboard.article.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ArticleSortBy {
    CREATE_AT("createdAt"),
    ID("id"),
    VIEWS("views"),
    TITLE("title");

    final String value;
}
