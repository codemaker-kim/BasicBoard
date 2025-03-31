package org.project.basicboard.bookmark.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.global.entity.BaseEntity;
import org.project.basicboard.user.domain.User;

@Entity
@Table(name = "bookmarks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Bookmark extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String articleTitle;

    @Builder
    public Bookmark(User user, String articleTitle) {
        this.user = user;
        this.articleTitle = articleTitle;
    }
}