package com.stuent.dpply.common.interceptor;

import com.stuent.dpply.common.exception.error.TokenExpiredException;
import com.stuent.dpply.domain.auth.entity.User;
import com.stuent.dpply.domain.token.service.TokenService;
import com.stuent.dpply.common.annotation.CheckAuthorization;
import com.stuent.dpply.common.extractor.AuthorizationExtractor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@AllArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
    private AuthorizationExtractor authExtractor;
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if(!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }

        CheckAuthorization userLoginToken = handlerMethod.getMethodAnnotation(CheckAuthorization.class);

        if (userLoginToken == null) {
            return true;
        }

        String token = authExtractor.extract(request, "Bearer");
        if (token == null || token.length() == 0) {
            return true;
        }

        User user = tokenService.verifyToken(token);
        if (user == null) {
            throw TokenExpiredException.EXCEPTION;
        }

        request.setAttribute("user", user);
        return true;
    }
}
