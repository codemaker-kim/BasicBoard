package org.project.basicboard.auth.application;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.auth.api.dto.request.UserInfoDto;
import org.project.basicboard.auth.api.dto.response.CreateAccessTokenResponse;
import org.project.basicboard.global.jwt.exception.InvalidRefreshTokenException;
import org.project.basicboard.global.jwt.exception.RefreshTokenNotFoundException;
import org.project.basicboard.global.jwt.service.TokenProvider;
import org.project.basicboard.refresh_token.domain.RefreshToken;
import org.project.basicboard.refresh_token.repository.RefreshTokenRepository;
import org.project.basicboard.user.domain.repository.UserRepository;
import org.project.basicboard.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public CreateAccessTokenResponse createAccessTokenForRefresh(String refreshToken) {
        RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(RefreshTokenNotFoundException::new);

        validateRefreshToken(refreshToken);

        UserInfoDto dto = getUserInfo(token);

        String newAccessToken = tokenProvider.createAccessTokenForRefresh(dto);

        return new CreateAccessTokenResponse(newAccessToken);
    }

    private UserInfoDto getUserInfo(RefreshToken token) {
        return UserInfoDto.from(
                userRepository.findById(token.getUser().getId())
                        .orElseThrow(UserNotFoundException::new));
    }

    private void validateRefreshToken(String refreshToken) {
        if (!tokenProvider.validateToken(refreshToken))
            throw new InvalidRefreshTokenException();
    }
}
