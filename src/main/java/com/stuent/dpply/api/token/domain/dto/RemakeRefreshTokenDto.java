package com.stuent.dpply.api.token.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class RemakeRefreshTokenDto {

    @NotEmpty
    private String refreshToken;
}
