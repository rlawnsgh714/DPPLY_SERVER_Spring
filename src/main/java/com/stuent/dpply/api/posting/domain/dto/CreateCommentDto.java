package com.stuent.dpply.api.posting.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class CreateCommentDto {

    @NotEmpty
    private String comment;
}
