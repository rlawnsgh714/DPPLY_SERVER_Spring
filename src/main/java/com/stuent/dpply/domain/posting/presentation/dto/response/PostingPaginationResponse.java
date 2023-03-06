package com.stuent.dpply.domain.posting.presentation.dto.response;

import com.stuent.dpply.domain.posting.entity.Posting;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostingPaginationResponse {

    private List<Posting> posting;
    private long pageCount;
}
