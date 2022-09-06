package com.stuent.requirement.api.auth.service;

import com.stuent.requirement.api.auth.domain.dto.DauthRequestDto;
import com.stuent.requirement.api.auth.domain.dto.DauthServerDto;
import com.stuent.requirement.api.auth.domain.dto.DodamLoginDto;
import com.stuent.requirement.api.auth.domain.dto.DodamOpenApiDto;
import com.stuent.requirement.api.auth.domain.entity.Auth;
import com.stuent.requirement.api.auth.domain.repository.AuthRepository;
import com.stuent.requirement.api.auth.domain.ro.LoginRo;
import com.stuent.requirement.common.config.properties.AppProperties;
import com.stuent.requirement.common.config.restemplate.RestTemplateConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final RestTemplateConfig restTemplateConfig;
    private final AppProperties appProperties;
    private final AuthRepository authRepository;

    @Override
    public LoginRo dodamLogin(DodamLoginDto dto) {
        DauthServerDto dauthToken = getDauthToken(dto.getCode());
        DodamOpenApiDto.DodamInfoData info = getDodamInfo(dauthToken.getAccess_token()).getData();
        Auth auth = authRepository.findById(info.getUniqueId()).orElseGet(() -> Auth.builder()
                .uniqueId(info.getUniqueId())
                .grade(info.getGrade())
                .room(info.getRoom())
                .number(info.getNumber())
                .name(info.getName())
                .email(info.getEmail())
                .profileImage(info.getProfileImage())
                .build());
//        Auth savedAuth = authRepository.save(auth);
        return new LoginRo(auth, dauthToken.getAccess_token(), dauthToken.getRefresh_token());
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
