package com.stuent.dpply.domain.auth.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DauthRequest {

    private String code;
    private String client_id;
    private String client_secret;
}
