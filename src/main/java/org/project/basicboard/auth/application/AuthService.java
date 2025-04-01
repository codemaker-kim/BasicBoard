package org.project.basicboard.auth.application;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.auth.api.dto.request.UserInfoDto;
import org.project.basicboard.auth.api.dto.request.LoginRequest;
import org.project.basicboard.auth.exception.NotAuthorizeUpdateNicknameException;
import org.project.basicboard.auth.exception.WrongPasswordException;
import org.project.basicboard.global.jwt.api.dto.TokenDto;
import org.project.basicboard.global.jwt.service.TokenProvider;
import org.project.basicboard.global.security.SecurityUtil;
import org.project.basicboard.refresh_token.domain.RefreshToken;
import org.project.basicboard.refresh_token.repository.RefreshTokenRepository;
import org.project.basicboard.user.domain.User;
import org.project.basicboard.user.domain.repository.UserRepository;
import org.project.basicboard.user.exception.AlreadyExistNicknameException;
import org.project.basicboard.user.exception.UserNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;

    public TokenDto login(LoginRequest dto) {
        User user = userRepository.findByUsername(dto.username())
                .orElseThrow(UserNotFoundException::new);

        validatePassword(user.getPassword(), dto.password());

        UserInfoDto userInfoDto = UserInfoDto.from(user);
        TokenDto tokens = tokenProvider.generateToken(userInfoDto);

        return updateRefreshToken(user, tokens);
    }

    public void updateNickname(Long id, String newNickname) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        String currentUsername = SecurityUtil.getCurrentUser();

        checkCurrentUser(user.getUsername(), currentUsername);

        validateNickname(newNickname);

        user.updateNickname(newNickname);
    }

    public void logout() {
        String currentUsername = SecurityUtil.getCurrentUser();
        SecurityUtil.clearAuthentication();

        User user = userRepository.findByUsername(currentUsername).get();

        refreshTokenRepository.deleteAllByUserId(user.getId());
    }

    private TokenDto updateRefreshToken(User user, TokenDto tokens) {
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(user.getId())
                .orElseGet(() -> refreshTokenRepository.save(
                        RefreshToken.builder()
                                .user(user)
                                .token(tokens.refreshToken())
                                .build()
                ));

        refreshToken.updateToken(tokens.refreshToken());

        return tokens;
    }


    private void checkCurrentUser(String username, String currentUsername) {
        if (!username.equals(currentUsername)) {
            throw new NotAuthorizeUpdateNicknameException();
        }
    }

    private void validatePassword(String userPassword, String requestPassword) {
        if (!passwordEncoder.matches(requestPassword, userPassword))
            throw new WrongPasswordException();
    }

    private void validateNickname(String nickname) {
        if (userRepository.existsByNickname(nickname))
            throw new AlreadyExistNicknameException();
    }
}