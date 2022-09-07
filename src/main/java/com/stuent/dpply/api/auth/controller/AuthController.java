package com.stuent.dpply.api.auth.controller;

import com.stuent.dpply.api.auth.domain.dto.DodamLoginDto;
import com.stuent.dpply.api.auth.domain.entity.User;
import com.stuent.dpply.api.auth.domain.ro.LoginRo;
import com.stuent.dpply.api.auth.service.AuthService;
import com.stuent.dpply.common.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
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

    @GetMapping
    public ResponseData<List<User>> getUsers() {
        List<User> userList = authService.getUsers();
        return new ResponseData<>(
                HttpStatus.OK,
                "유저 조회 성공",
                userList
        );
    }
}
