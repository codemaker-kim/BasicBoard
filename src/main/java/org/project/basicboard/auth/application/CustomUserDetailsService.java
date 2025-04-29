package org.project.basicboard.auth.application;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.auth.CustomUserDetails;
import org.project.basicboard.user.domain.User;
import org.project.basicboard.user.repository.UserRepository;
import org.project.basicboard.user.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    //todo: 추가적으로 구성할게 있음.
    // 검증 넣으면 좋을 것 같다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);

        return new CustomUserDetails(user);
    }
}