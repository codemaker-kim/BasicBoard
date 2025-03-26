package org.project.basicboard.article.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.project.basicboard.global.entity.BaseEntity;
import org.project.basicboard.user.domain.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    private Integer views;

    private Integer likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Article(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.views = 0;
        this.likeCount = 0;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
