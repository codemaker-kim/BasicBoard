package org.project.basicboard.auth.application;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.auth.api.dto.UserInfoDto;
import org.project.basicboard.auth.api.dto.request.LoginRequest;
import org.project.basicboard.auth.exception.NotAuthorizeUpdateNicknameException;
import org.project.basicboard.global.jwt.api.dto.TokenDto;
import org.project.basicboard.global.security.SecurityUtil;
import org.project.basicboard.user.application.UserService;
import org.project.basicboard.user.domain.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenService tokenService;
    private final UserService userService;
    private final SecurityUtil securityUtil;

    public TokenDto login(LoginRequest dto) {
        userService.validateUser(dto);
        //리프레시 토큰 저장로직 필요
        UserInfoDto userInfoDto = getUserInfo(dto.username());

        return tokenService.generateToken(userInfoDto);
    }

    public void updateNickname(Long id, String newNickname) {
        User user = userService.findById(id);
        String currentUsername = securityUtil.getCurrentUser();

        isSameUsername(currentUsername, newNickname);
        userService.isExistNickname(newNickname);

        user.updateNickname(newNickname);
    }

    private void isSameUsername(String username, String currentUsername) {
        if (username.equals(currentUsername))
            throw new NotAuthorizeUpdateNicknameException();
    }

    private UserInfoDto getUserInfo(String username) {
        User user = userService.findByUsername(username);

        return UserInfoDto.from(user);
    }
}