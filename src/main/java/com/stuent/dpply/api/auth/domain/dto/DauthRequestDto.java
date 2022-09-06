package com.stuent.dpply.api.auth.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DauthRequestDto {

    private String code;
    private String client_id;
    private String client_secret;
}
