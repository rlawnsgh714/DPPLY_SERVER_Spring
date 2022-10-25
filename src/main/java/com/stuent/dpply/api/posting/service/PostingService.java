package com.stuent.dpply.api.posting.service;

import com.stuent.dpply.api.auth.domain.entity.User;
import com.stuent.dpply.api.posting.domain.dto.CreateCommentDto;
import com.stuent.dpply.api.posting.domain.dto.CreatePostDto;
import com.stuent.dpply.api.posting.domain.dto.ModifyCommentDto;
import com.stuent.dpply.api.posting.domain.dto.ModifyPostDto;
import com.stuent.dpply.api.posting.domain.entity.Posting;
import com.stuent.dpply.api.posting.domain.entity.PostingComment;
import com.stuent.dpply.api.posting.domain.enums.SortMethod;
import com.stuent.dpply.api.posting.domain.enums.PostingStatus;

import java.util.List;

public interface PostingService {

    List<Posting> getPostByStatusAndSort(PostingStatus status, SortMethod sort);

    List<Posting> getPostByPageAndLimit(int page, int limit);

    Posting getPostById(Long id);

    void createPost(User user, CreatePostDto dto);

    void modifyPost(User user, ModifyPostDto dto);

    void deletePost(User user, Long id);

    void soledPost(Long id);

    void refusePost(Long id);

    void signSympathy(User user, Long id);

    void cancelSympathy(User user, Long id);

    List<PostingComment> getPostingComment(Long id);

    void createComment(User user, Long id, CreateCommentDto dto);

    void modifyComment(User user, Long id, ModifyCommentDto dto);
}
