package org.project.basicboard.comment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.global.entity.BaseEntity;
import org.project.basicboard.user.domain.User;

@Entity
@Table(name = "comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @Builder
    public Comment(String content, User user, Article article) {
        this.content = content;
        this.user = user;
        this.article = article;
    }

    public void update(String content) {
        this.content = content;
    }
}
