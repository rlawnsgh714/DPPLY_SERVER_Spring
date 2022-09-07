package com.stuent.dpply.common.interceptor;

import com.stuent.dpply.api.auth.domain.entity.User;
import com.stuent.dpply.api.token.service.TokenService;
import com.stuent.dpply.common.annotation.CheckAuthorization;
import com.stuent.dpply.common.extractor.AuthorizationExtractor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
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
        if(!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        CheckAuthorization checkAuthorization = handlerMethod.getMethodAnnotation(CheckAuthorization.class);

        if (checkAuthorization == null) {
            return true;
        }

        String token = authExtractor.extract(request, "Bearer");
        if (token == null || token.length() == 0) {
            return true;
        }

        if (tokenService.verifyToken(token) == null) {
            throw new IllegalArgumentException("유효하지 않은 토큰");
        }

        User auth = tokenService.verifyToken(token);
        request.setAttribute("authId", auth);
        return true;
    }
}
