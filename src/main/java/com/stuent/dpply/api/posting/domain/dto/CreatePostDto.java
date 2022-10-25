package com.stuent.dpply.api.posting.domain.dto;

import com.stuent.dpply.api.posting.domain.enums.PostingTag;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class CreatePostDto {

    @NotEmpty
    private String title;
    @NotEmpty
    private String text;
    @NotEmpty
    private PostingTag tag;
    @NotEmpty
    private String imageUrl;
}
