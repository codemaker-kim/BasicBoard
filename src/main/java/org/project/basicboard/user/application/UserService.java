package org.project.basicboard.user.application;

import lombok.RequiredArgsConstructor;
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
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserJoinResponse joinProcess(UserJoinRequest dto) {
        isExists(dto.username(), dto.nickname());

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

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

    private void isExists(String username, String nickname) {
        if (userRepository.existsByUsername(username))
            throw new UserAlreadyExistException();

        if (userRepository.existsByNickname(nickname))
            throw new AlreadyExistNicknameException();
    }
}