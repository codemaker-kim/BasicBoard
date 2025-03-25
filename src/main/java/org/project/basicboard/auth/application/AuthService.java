package org.project.basicboard.auth.application;

import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.project.basicboard.auth.api.dto.UserInfoDto;
import org.project.basicboard.auth.api.dto.request.LoginRequest;
import org.project.basicboard.global.jwt.api.dto.TokenDto;
import org.project.basicboard.user.application.UserService;
import org.project.basicboard.user.domain.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenService tokenService;
    private final UserService userService;

    public TokenDto login(LoginRequest dto) {
        userService.validateUser(dto);

        UserInfoDto userInfoDto = getUserInfo(dto.username());

        return tokenService.generateToken(userInfoDto);
    }

    private UserInfoDto getUserInfo(String username) {
        User user = userService.findByUsername(username);

        return UserInfoDto.from(user);
    }
}