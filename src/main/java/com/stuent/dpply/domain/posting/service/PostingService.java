package com.stuent.dpply.domain.posting.service;

import com.stuent.dpply.domain.auth.entity.User;
import com.stuent.dpply.domain.posting.presentation.dto.request.CreateCommentRequest;
import com.stuent.dpply.domain.posting.presentation.dto.request.CreatePostRequest;
import com.stuent.dpply.domain.posting.presentation.dto.request.ModifyCommentRequest;
import com.stuent.dpply.domain.posting.presentation.dto.request.ModifyPostRequest;
import com.stuent.dpply.domain.posting.entity.Posting;
import com.stuent.dpply.domain.posting.entity.PostingComment;
import com.stuent.dpply.common.enums.PostingTag;
import com.stuent.dpply.common.enums.SortMethod;
import com.stuent.dpply.common.enums.PostingStatus;
import com.stuent.dpply.domain.posting.presentation.dto.response.PostingPaginationResponse;

import java.util.List;

public interface PostingService {

    List<Posting> getPostByStatusAndSort(PostingStatus status, SortMethod sort);
    PostingPaginationResponse getPostByPage(int page);

    List<Posting> getMyPost(User user, PostingStatus status, PostingTag tag);

    List<Posting> getPostByTag(PostingTag tag);

    Posting getPostById(Long id);

    void createPost(User user, CreatePostRequest dto);

    void modifyPost(User user, ModifyPostRequest dto);

    void deletePost(User user, Long id);

    void solvedPost(Long id);

    void refusePost(Long id);

    void signSympathy(User user, Long id);

    void cancelSympathy(User user, Long id);

    List<PostingComment> getPostingComment(Long id);

    void createComment(User user, Long id, CreateCommentRequest dto);

    void modifyComment(User user, Long id, ModifyCommentRequest dto);

    void deleteComment(User user, Long id);
}
