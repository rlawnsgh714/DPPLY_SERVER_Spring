package com.stuent.dpply.domain.auth.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DauthLoginResponse {

    private String name;
    private String profileImage;
    private String location;
}
