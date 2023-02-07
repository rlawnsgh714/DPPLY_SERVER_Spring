package com.stuent.dpply.domain.posting.presentation.dto.request;

import com.stuent.dpply.common.enums.PostingTag;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CreatePostRequest {

    @NotEmpty
    private String title;
    @NotEmpty
    private String text;
    @NotNull
    private PostingTag tag;
    @NotEmpty
    private String imageUrl;
}
