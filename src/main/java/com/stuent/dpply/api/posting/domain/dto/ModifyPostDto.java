package com.stuent.dpply.api.posting.domain.dto;

import com.stuent.dpply.api.posting.domain.enums.PostingTag;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModifyPostDto {
    private Long postingId;
    private String title;
    private String text;
    private PostingTag tag;
}
