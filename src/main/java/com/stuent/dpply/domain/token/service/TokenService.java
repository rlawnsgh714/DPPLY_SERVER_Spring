package com.stuent.dpply.domain.token.service;

import com.stuent.dpply.domain.auth.entity.User;
import com.stuent.dpply.domain.token.presentation.dto.request.RemakeRefreshTokenRequest;
import com.stuent.dpply.common.enums.JWT;

public interface TokenService {

    String generateToken(String id, JWT jwt);

    User verifyToken(String token);

    String remakeAccessToken(RemakeRefreshTokenRequest refreshToken);
}
