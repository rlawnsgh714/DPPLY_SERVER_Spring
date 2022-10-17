package com.stuent.dpply.api.auth.domain.ro;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DauthLoginRo {

    private String name;
    private String profileImage;
    private String location;
}
