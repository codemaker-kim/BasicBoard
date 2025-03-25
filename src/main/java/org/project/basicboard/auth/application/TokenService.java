package org.project.basicboard.auth.application;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.global.jwt.api.dto.TokenDto;
import org.project.basicboard.global.jwt.service.TokenProvider;
import org.project.basicboard.refresh_token.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenDto generateToken(String username) {
        return tokenProvider.generateToken(username);
    }
}
