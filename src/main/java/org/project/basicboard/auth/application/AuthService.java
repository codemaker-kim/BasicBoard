package org.project.basicboard.auth.application;

import lombok.RequiredArgsConstructor;
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

        return tokenService.generateToken(dto.username());
    }
}