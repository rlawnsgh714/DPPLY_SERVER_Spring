package com.stuent.dpply.api.auth.service;

import com.stuent.dpply.api.auth.domain.dto.*;
import com.stuent.dpply.api.auth.domain.entity.User;
import com.stuent.dpply.api.auth.domain.enums.UserRole;
import com.stuent.dpply.api.auth.domain.repository.AuthRepository;
import com.stuent.dpply.api.auth.domain.ro.DauthServerDto;
import com.stuent.dpply.api.auth.domain.ro.LoginRo;
import com.stuent.dpply.api.token.domain.enums.JWT;
import com.stuent.dpply.api.token.service.TokenService;
import com.stuent.dpply.common.config.properties.AppProperties;
import com.stuent.dpply.common.config.restemplate.RestTemplateConfig;
import com.stuent.dpply.common.exception.ForbiddenException;
import com.stuent.dpply.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final RestTemplateConfig restTemplateConfig;
    private final AppProperties appProperties;
    private final AuthRepository authRepository;
    private final TokenService tokenService;

    @Override
    public LoginRo dodamLogin(DodamLoginDto dto) {
        DauthServerDto dauthToken = getDauthToken(dto.getCode());
        if(dauthToken.getAccess_token() == null) {
            throw new ForbiddenException("변조된 code입니다");
        }
        DodamOpenApiDto.DodamInfoData info = getDodamInfo(dauthToken.getAccess_token()).getData();
        User user = authRepository.findById(info.getUniqueId()).orElseGet(() -> User.builder()
                .uniqueId(info.getUniqueId())
                .grade(info.getGrade())
                .room(info.getRoom())
                .number(info.getNumber())
                .name(info.getName())
                .email(info.getEmail())
                .profileImage(info.getProfileImage())
                .role(UserRole.valueOfNumber(info.getAccessLevel()))
                .build());
        User savedUser = authRepository.save(user);
        String token = tokenService.generateToken(user.getUniqueId(), JWT.ACCESS);
        String refreshToken = tokenService.generateToken(user.getUniqueId(), JWT.REFRESH);
        return new LoginRo(savedUser, token, refreshToken);
    }

    @Override
    public List<User> getUsers() {
        return authRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        return authRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 아이디를 가진 유저는 존재하지 않습니다"));
    }

    private DauthServerDto getDauthToken(String code) {
        return restTemplateConfig.dauthTemplate()
                .postForObject("/token", new HttpEntity<>(
                        DauthRequestDto.builder()
                                .code(code)
                                .client_id(appProperties.getCLIENT_ID())
                                .client_secret(appProperties.getCLIENT_SECRET())
                                .build(),
                        null
                ), DauthServerDto.class);
    }

    private DodamOpenApiDto getDodamInfo(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", "Bearer " + token);
        return restTemplateConfig.dodamOpenApiTemplate()
                .exchange(
                        "/user",
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        DodamOpenApiDto.class).getBody();
    }
}
