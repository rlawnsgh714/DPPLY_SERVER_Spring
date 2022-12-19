package com.stuent.dpply.domain.token.presentation;

import com.stuent.dpply.domain.token.presentation.dto.request.RemakeRefreshTokenRequest;
import com.stuent.dpply.domain.token.service.TokenService;
import com.stuent.dpply.common.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/refresh")
    public ResponseData<String> remakeAccessToken(
            @RequestBody @Valid RemakeRefreshTokenRequest dto
    ) {
        String token = tokenService.remakeAccessToken(dto);
        return new ResponseData<>(
                HttpStatus.OK,
                "토큰 재발급 성공",
                token
        );
    }
}
