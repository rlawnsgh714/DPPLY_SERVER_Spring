package com.stuent.dpply.domain.auth.service;

import com.stuent.dpply.domain.auth.entity.User;
import com.stuent.dpply.common.enums.UserRole;
import com.stuent.dpply.domain.auth.entity.repository.UserRepository;
import com.stuent.dpply.domain.auth.presentation.dto.response.DauthServerResponse;
import com.stuent.dpply.domain.auth.presentation.dto.response.LoginResponse;
import com.stuent.dpply.domain.auth.exception.DAuthCodeNotFoundException;
import com.stuent.dpply.domain.auth.exception.UserNotFoundException;
import com.stuent.dpply.domain.auth.presentation.dto.request.DauthRequest;
import com.stuent.dpply.domain.auth.presentation.dto.request.DodamLoginRequest;
import com.stuent.dpply.domain.auth.presentation.dto.response.DodamOpenApiResponse;
import com.stuent.dpply.common.enums.JWT;
import com.stuent.dpply.domain.token.service.TokenService;
import com.stuent.dpply.common.config.properties.AppProperties;
import com.stuent.dpply.common.config.restemplate.RestTemplateConfig;
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
    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Override
    public LoginResponse dodamLogin(DodamLoginRequest dto) {
        DauthServerResponse dauthToken = getDauthToken(dto.getCode());
        if(dauthToken.getAccess_token() == null) {
            throw DAuthCodeNotFoundException.EXCEPTION;
        }
        DodamOpenApiResponse.DodamInfoData info = getDodamInfo(dauthToken.getAccess_token()).getData();
        User user = userRepository.findById(info.getUniqueId()).orElseGet(() -> User.builder()
                .uniqueId(info.getUniqueId())
                .grade(info.getGrade())
                .room(info.getRoom())
                .number(info.getNumber())
                .name(info.getName())
                .email(info.getEmail())
                .profileImage(info.getProfileImage())
                .role(UserRole.valueOfNumber(info.getAccessLevel()))
                .build());
        user.updateUserData(
                info.getGrade(),
                info.getRoom(),
                info.getNumber(),
                info.getName(),
                info.getEmail(),
                info.getProfileImage());
        User savedUser = userRepository.save(user);
        String token = tokenService.generateToken(user.getUniqueId(), JWT.ACCESS);
        String refreshToken = tokenService.generateToken(user.getUniqueId(), JWT.REFRESH);
        return new LoginResponse(savedUser, token, refreshToken);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    private DauthServerResponse getDauthToken(String code) {
        return restTemplateConfig.dauthTemplate()
                .postForObject("/token", new HttpEntity<>(
                        DauthRequest.builder()
                                .code(code)
                                .client_id(appProperties.getCLIENT_ID())
                                .client_secret(appProperties.getCLIENT_SECRET())
                                .build(),
                        null
                ), DauthServerResponse.class);
    }

    private DodamOpenApiResponse getDodamInfo(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", "Bearer " + token);
        return restTemplateConfig.dodamOpenApiTemplate()
                .exchange(
                        "/user",
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        DodamOpenApiResponse.class).getBody();
    }
}
