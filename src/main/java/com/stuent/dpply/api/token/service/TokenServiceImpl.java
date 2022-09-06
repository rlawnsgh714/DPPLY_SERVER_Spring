package com.stuent.dpply.api.token.service;

import com.stuent.dpply.api.auth.domain.entity.Auth;
import com.stuent.dpply.api.auth.domain.repository.AuthRepository;
import com.stuent.dpply.api.token.domain.enums.JWT;
import com.stuent.dpply.common.config.properties.AppProperties;
import com.stuent.dpply.common.exception.BadRequestException;
import com.stuent.dpply.common.exception.GoneException;
import com.stuent.dpply.common.exception.InternalServerException;
import com.stuent.dpply.common.exception.NotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
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
    private final AuthRepository authRepository;

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
        claims.put("authId", id);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(jwt.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiredAt)
                .signWith(signatureAlgorithm, secretKey)
                .compact();
    }

    private Claims parseToken(String token, JWT jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(
                            jwt == JWT.ACCESS ?
                                    appProperties.getACCESS_SECRET() : appProperties.getREFRESH_SECRET()
                    )
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new GoneException("토큰이 만료되었습니다");
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("토큰이 없습니다");
        } catch (Exception e) {
            throw new InternalServerException("토큰 해석 중 에러 발생");
        }
    }

    @Override
    public Auth verifyToken(String token) {
        return authRepository.findById(
                parseToken(token, JWT.ACCESS).get("authId").toString())
                .orElseThrow(() -> new NotFoundException("해당 아이디는 존재하지 않습니다"));
    }
}
