package com.stuent.dpply.domain.auth.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DauthServerResponse {

    private String access_token;
    private String refresh_token;
    private String token_type;
    private String expires_in;
}
