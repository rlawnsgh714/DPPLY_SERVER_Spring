package com.stuent.dpply.domain.posting.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class CreateCommentRequest {

    @NotEmpty
    private String comment;
}
