package com.stuent.dpply.api.posting.service;

import com.stuent.dpply.api.posting.domain.entity.Posting;

import java.util.List;

public interface PostingService {

    List<Posting> getWaitingPost();

    List<Posting> getSolvedPost();
}
