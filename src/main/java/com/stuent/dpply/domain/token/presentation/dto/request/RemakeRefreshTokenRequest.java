package com.stuent.dpply.domain.token.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class RemakeRefreshTokenRequest {

    @NotEmpty
    private String refreshToken;
}
