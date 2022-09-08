package com.stuent.dpply.api.posting.service;

import com.stuent.dpply.api.auth.domain.entity.User;
import com.stuent.dpply.api.posting.domain.dto.CreatePostDto;
import com.stuent.dpply.api.posting.domain.dto.ModifyPostDto;
import com.stuent.dpply.api.posting.domain.entity.Posting;

import java.util.List;

public interface PostingService {

    List<Posting> getWaitingPost();

    List<Posting> getSolvedPost();

    void createPost(User user, CreatePostDto dto);

    void modifyPost(User user, ModifyPostDto dto);

    void deletePost(User user, int id);

    void soledPost(User user, int id);
}
