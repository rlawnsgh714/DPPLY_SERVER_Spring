package com.stuent.dpply.api.posting.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class CreatePostDto {
    @NotEmpty
    private String text;
}
