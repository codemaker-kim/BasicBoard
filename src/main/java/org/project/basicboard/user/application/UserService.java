package org.project.basicboard.user.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.basicboard.auth.api.dto.request.LoginRequest;
import org.project.basicboard.auth.exception.WrongPasswordException;
import org.project.basicboard.user.api.dto.request.UserJoinRequest;
import org.project.basicboard.user.api.dto.response.UserJoinResponse;
import org.project.basicboard.user.domain.User;
import org.project.basicboard.user.domain.repository.UserRepository;
import org.project.basicboard.user.exception.AlreadyExistNicknameException;
import org.project.basicboard.user.exception.UserAlreadyExistException;
import org.project.basicboard.user.exception.UserNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserJoinResponse joinProcess(UserJoinRequest dto) {
        isExistUsername(dto.username());
        isExistNickname(dto.nickname());

        User user = createUser(dto);

        userRepository.save(user);

        return UserJoinResponse.from(user);
    }

    public void validateUser(LoginRequest dto) {
        String password = findByUsername(dto.username()).getPassword();

        if (!passwordEncoder.matches(dto.password(), password))
            throw new WrongPasswordException();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

    private User createUser(UserJoinRequest dto) {
        return User.builder()
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .nickname(dto.nickname())
                .build();
    }

    private void isExistUsername(String username) {
        if (userRepository.existsByUsername(username))
            throw new UserAlreadyExistException();
    }

    private void isExistNickname(String nickname) {
        if (userRepository.existsByNickname(nickname))
            throw new AlreadyExistNicknameException();
    }
}