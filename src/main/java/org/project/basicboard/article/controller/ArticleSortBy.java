package org.project.basicboard.article.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ArticleSortBy {
    CREATE_AT("createdAt"),
    ID("id"),
    VIEWS("views"),
    TITLE("title");

    final String value;
}