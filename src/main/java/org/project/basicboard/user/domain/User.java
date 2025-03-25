package org.project.basicboard.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.project.basicboard.global.entity.BaseEntity;

import static org.project.basicboard.user.domain.Role.ROLE_USER;

@Table(name = "users")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Builder
    User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.role = ROLE_USER;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
}