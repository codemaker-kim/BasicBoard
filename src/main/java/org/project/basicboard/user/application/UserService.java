package org.project.basicboard.user.application;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.user.application.dto.request.UserJoinServiceRequest;
import org.project.basicboard.user.application.dto.response.UserJoinServiceResponse;
import org.project.basicboard.user.domain.User;
import org.project.basicboard.user.exception.UserNotFoundException;
import org.project.basicboard.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserInfoChecker checker;
    private final UserMapper mapper;

    public UserJoinServiceResponse joinProcess(UserJoinServiceRequest request) {
        checker.checkJoinInfo(request.nickname(), request.username());

        User user = createUser(request.username(), request.password(), request.nickname());
        userRepository.save(user);

        return mapper.toServiceResponse(user);
    }

    @Transactional
    public void updateNickname(String username, String nicknameReq) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);

        checker.existNicknameCheck(nicknameReq);

        user.updateNickname(nicknameReq);
    }

    private User createUser(String username, String password, String nickname) {
        String encodedPassword = encodePassword(password);

        return User.joinOf(username, encodedPassword, nickname);
    }

    private String encodePassword(final String password) {
        return passwordEncoder.encode(password);
    }
}