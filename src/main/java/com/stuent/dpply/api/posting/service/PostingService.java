package com.stuent.dpply.api.posting.service;

import com.stuent.dpply.api.auth.domain.entity.User;
import com.stuent.dpply.api.posting.domain.dto.CreatePostDto;
import com.stuent.dpply.api.posting.domain.dto.ModifyPostDto;
import com.stuent.dpply.api.posting.domain.entity.Posting;
import com.stuent.dpply.api.posting.domain.enums.SortMethod;
import com.stuent.dpply.api.posting.domain.enums.PostingStatus;

import java.util.List;

public interface PostingService {

    List<Posting> getPostByStatusAndSort(PostingStatus status, SortMethod sort);

    void createPost(User user, CreatePostDto dto);

    void modifyPost(User user, ModifyPostDto dto);

    void deletePost(User user, int id);

    void soledPost(int id);

    void refusePost(int id);

    void signSympathy(User user, int id);

    void cancelSympathy(User user, int id);
}
