package com.stuent.dpply.api.token.service;

import com.stuent.dpply.api.token.domain.enums.JWT;
import com.stuent.dpply.common.config.properties.AppProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService{

    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private final AppProperties appProperties;
    private final long JWT_ACCESS_EXPIRE = 60 * 60 * 1000;
    private final long JWT_REFRESH_EXPIRE = 60 * 60 * 1000 * 24 * 7;

    @Override
    public String generateToken(String id, JWT jwt) {
        Date expiredAt = new Date();
        String secretKey;

        if(jwt.equals(JWT.ACCESS)){
            expiredAt = new Date(expiredAt.getTime() + JWT_ACCESS_EXPIRE);
            secretKey = appProperties.getACCESS_SECRET();
        }else{
            expiredAt = new Date(expiredAt.getTime() + JWT_REFRESH_EXPIRE);
            secretKey = appProperties.getREFRESH_SECRET();
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("authID", id);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(jwt.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiredAt)
                .signWith(signatureAlgorithm, secretKey)
                .compact();
    }
}
