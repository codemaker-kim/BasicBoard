package org.project.basicboard.global.annotation.resolver;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.project.basicboard.global.annotation.AuthUsername;
import org.project.basicboard.global.jwt.TokenUtil;
import org.project.basicboard.global.jwt.service.TokenProvider;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class AuthUsernameAnnotationResolver implements HandlerMethodArgumentResolver {

    private final TokenProvider tokenProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthUsername.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String authHeader = request.getHeader(TokenUtil.AUTH_HEADER.getValue());

        if (checkTokenForm(authHeader)) {
            String token = tokenParse(authHeader);

            return tokenProvider.getUsernameFromToken(token);
        }

        // TODO: 상황에 맞는 예외 던지게 바꾸기
        return null;
    }

    private boolean checkTokenForm(String authHeader) {
        return authHeader != null && authHeader.startsWith(TokenUtil.JWT_PREFIX.getValue());
    }

    private String tokenParse(String authHeader) {
        return authHeader.substring(TokenUtil.JWT_PREFIX.getValue().length());
    }
}
