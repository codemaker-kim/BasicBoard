package org.project.basicboard.auth.application;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.auth.application.dto.request.LoginServiceRequest;
import org.project.basicboard.auth.application.dto.request.UserInfoDto;
import org.project.basicboard.auth.application.dto.response.LoginServiceResponse;
import org.project.basicboard.auth.exception.WrongPasswordException;
import org.project.basicboard.global.jwt.api.dto.TokenDto;
import org.project.basicboard.global.jwt.service.TokenProvider;
import org.project.basicboard.user.domain.User;
import org.project.basicboard.user.exception.UserNotFoundException;
import org.project.basicboard.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper mapper;

    @Transactional
    public LoginServiceResponse login(LoginServiceRequest dto) {
        User user = userRepository.findByUsername(dto.username())
                .orElseThrow(UserNotFoundException::new);

        validatePassword(user.getPassword(), dto.password());

        TokenDto tokens = createTokens(user);

        return updateRefreshToken(user, tokens);
    }

    private TokenDto createTokens(User user) {
        UserInfoDto userInfoDto = mapper.toUserInfoDto(user);

        return tokenProvider.generateToken(userInfoDto);
    }

    // todo : 사용자 .get바로 하지말고 예외 처리하기
    public void logout() {

    }

    // todo: 토큰이 존재하지 않을 때 예외 처리
    private LoginServiceResponse updateRefreshToken(User user, TokenDto tokens) {
        user.updateRefreshToken(tokens.refreshToken());

        return mapper.toLoginServiceResponse(tokens);
    }

    private void validatePassword(String userPassword, String requestPassword) {
        if (!passwordEncoder.matches(requestPassword, userPassword))
            throw new WrongPasswordException();
    }
}