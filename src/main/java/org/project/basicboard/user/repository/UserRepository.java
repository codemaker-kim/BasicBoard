package org.project.basicboard.user.repository;

import org.project.basicboard.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    Boolean existsByNickname(String nickname);

    Optional<User> findByRefreshToken(String refreshToken);
}