package com.stuent.dpply.common.interceptor;

import com.stuent.dpply.api.auth.domain.entity.Auth;
import com.stuent.dpply.api.token.service.TokenService;
import com.stuent.dpply.common.extractor.AuthorizationExtractor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@AllArgsConstructor
public class BearerAuthInterceptor implements HandlerInterceptor {
    private AuthorizationExtractor authExtractor;
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        String token = authExtractor.extract(request, "Bearer");
        if (token == null || token.length() == 0) {
            return true;
        }

        if (tokenService.verifyToken(token) == null) {
            throw new IllegalArgumentException("유효하지 않은 토큰");
        }

        Auth auth = tokenService.verifyToken(token);
        request.setAttribute("authId", auth);
        return true;
    }
}
