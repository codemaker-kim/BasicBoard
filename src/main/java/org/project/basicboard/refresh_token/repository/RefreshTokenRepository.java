package org.project.basicboard.refresh_token.repository;

import org.project.basicboard.refresh_token.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUserId(Long userId);

    Boolean existsByToken(String token);

    void deleteAllByUserId(Long userId);
}