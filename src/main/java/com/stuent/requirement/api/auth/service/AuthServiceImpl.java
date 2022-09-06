package com.stuent.requirement.api.auth.service;

import com.stuent.requirement.api.auth.domain.dto.DauthRequestDto;
import com.stuent.requirement.api.auth.domain.dto.DauthServerDto;
import com.stuent.requirement.api.auth.domain.dto.DodamLoginDto;
import com.stuent.requirement.api.auth.domain.dto.DodamOpenApiDto;
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

    @Override
    public LoginRo dodamLogin(DodamLoginDto dto) {
        String accessToken = getDauthToken(dto.getCode()).getAccess_token();
        DodamOpenApiDto dodamOpenApiDto = getDodamInfo(accessToken);
        return null;
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
