package org.project.basicboard.user.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.basicboard.user.api.dto.request.UserJoinRequest;
import org.project.basicboard.user.api.dto.response.UserJoinResponse;
import org.project.basicboard.user.domain.User;
import org.project.basicboard.user.domain.repository.UserRepository;
import org.project.basicboard.user.exception.AlreadyExistNicknameException;
import org.project.basicboard.user.exception.UserAlreadyExistException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserJoinResponse joinProcess(UserJoinRequest dto) {
        existUsernameCheck(dto.username());
        existNicknameCheck(dto.nickname());

        User user = createUser(dto);

        userRepository.save(user);

        return UserJoinResponse.from(user);
    }

    private User createUser(UserJoinRequest dto) {
        return User.builder()
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .nickname(dto.nickname())
                .build();
    }

    private void existUsernameCheck(String username) {
        if (userRepository.existsByUsername(username))
            throw new UserAlreadyExistException();
    }

    private void existNicknameCheck(String nickname) {
        if (userRepository.existsByNickname(nickname))
            throw new AlreadyExistNicknameException();
    }
}