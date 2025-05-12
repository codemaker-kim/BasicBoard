package org.project.basicboard.auth.application;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.auth.application.dto.request.AccessTokenServiceRequest;

import org.project.basicboard.auth.application.dto.request.UserInfoRequest;
import org.project.basicboard.auth.application.dto.response.AccessTokenServiceResponse;
import org.project.basicboard.global.jwt.exception.InvalidRefreshTokenException;
import org.project.basicboard.global.jwt.service.TokenProvider;
import org.project.basicboard.user.domain.User;
import org.project.basicboard.user.exception.NoUserMatchRefreshTokenException;
import org.project.basicboard.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    public AccessTokenServiceResponse createAccessTokenForRefresh(AccessTokenServiceRequest request) {
        final String refreshToken = request.refreshToken();

        validateRefreshToken(refreshToken);
        User findUser = userRepository.findByRefreshToken(refreshToken)
                .orElseThrow(NoUserMatchRefreshTokenException::new);

        return getAccessTokenServiceResponse(findUser.getId(), findUser.getUsername());
    }

    private AccessTokenServiceResponse getAccessTokenServiceResponse(Long id, String username) {
        UserInfoRequest userInfo = getUserInfo(id, username);
        String newAccessToken = tokenProvider.generateAccessTokenForUserInfo(userInfo);

        return new AccessTokenServiceResponse(newAccessToken);
    }

    private UserInfoRequest getUserInfo(Long id, String username) {
        return UserInfoRequest.builder()
                .id(id)
                .username(username)
                .build();
    }

    private void validateRefreshToken(String refreshToken) {
        if (!tokenProvider.validateToken(refreshToken))
            throw new InvalidRefreshTokenException();
    }
}
