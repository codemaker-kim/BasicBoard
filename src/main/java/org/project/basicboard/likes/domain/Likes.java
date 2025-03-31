package org.project.basicboard.likes.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.project.basicboard.global.entity.BaseEntity;
import org.project.basicboard.user.domain.User;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Likes extends BaseEntity {

    @Column(nullable = false)
    private String articleTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    Likes(User user, String articleTitle) {
        this.user = user;
        this.articleTitle = articleTitle;
    }
}
