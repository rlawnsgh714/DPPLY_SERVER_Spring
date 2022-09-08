package com.stuent.dpply.api.posting.service;

import com.stuent.dpply.api.auth.domain.entity.User;
import com.stuent.dpply.api.posting.domain.dto.CreatePostDto;
import com.stuent.dpply.api.posting.domain.entity.Posting;
import com.stuent.dpply.api.posting.domain.enums.PostingStatus;
import com.stuent.dpply.api.posting.domain.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostingServiceImpl implements PostingService{

    private final PostingRepository postingRepository;

    @Override
    public List<Posting> getWaitingPost() {
        return postingRepository.findByStatus(PostingStatus.WAITING);
    }

    @Override
    public List<Posting> getSolvedPost() {
        return postingRepository.findByStatus(PostingStatus.SOLVED);
    }

    @Override
    public void createPost(User user, CreatePostDto dto) {
        Posting posting = Posting.builder()
                .text(dto.getText())
                .user(user)
                .build();
        postingRepository.save(posting);
    }
}
