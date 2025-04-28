package org.project.basicboard.user.application;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.user.exception.AlreadyExistNicknameException;
import org.project.basicboard.user.exception.UserAlreadyExistException;
import org.project.basicboard.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserInfoChecker {

    private final UserRepository userRepository;

    void checkJoinInfo(String username, String nickname) {
        existUsernameCheck(username);
        existNicknameCheck(nickname);
    }

    void existNicknameCheck(String nickname) {
        if (userRepository.existsByNickname(nickname))
            throw new AlreadyExistNicknameException();
    }

    private void existUsernameCheck(String username) {
        if (userRepository.existsByUsername(username))
            throw new UserAlreadyExistException();
    }
}