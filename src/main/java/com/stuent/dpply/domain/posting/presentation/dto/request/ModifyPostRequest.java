package com.stuent.dpply.domain.posting.presentation.dto.request;

import com.stuent.dpply.common.enums.PostingTag;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModifyPostRequest {
    private Long postingId;
    private String title;
    private String text;
    private PostingTag tag;
}
