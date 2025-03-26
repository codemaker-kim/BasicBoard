package org.project.basicboard.global.security;

import org.project.basicboard.global.security.exception.NoAuthenticationInfoException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        validateAuth(authentication);

        return authentication.getName();
    }

    public void clearAuthentication() {
        SecurityContextHolder.clearContext();
    }

    private void validateAuth(Authentication authentication) {
        if (authentication == null || authentication.getName() == null)
            throw new NoAuthenticationInfoException();
    }


}
