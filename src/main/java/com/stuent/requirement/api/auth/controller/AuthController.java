package com.stuent.requirement.api.auth.controller;

import com.stuent.requirement.api.auth.domain.dto.DodamLoginDto;
import com.stuent.requirement.api.auth.domain.ro.LoginRo;
import com.stuent.requirement.api.auth.service.AuthService;
import com.stuent.requirement.common.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseData<LoginRo> login(
            @RequestBody @Valid DodamLoginDto dto
    ) {
        LoginRo loginRo = authService.dodamLogin(dto);
        return new ResponseData<>(
                HttpStatus.OK,
                "로그인 성공",
                loginRo
        );
    }
}
