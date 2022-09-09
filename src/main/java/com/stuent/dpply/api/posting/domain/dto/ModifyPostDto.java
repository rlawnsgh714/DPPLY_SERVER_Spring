package com.stuent.dpply.api.posting.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModifyPostDto {
    private int postingId;
    private String text;
}
