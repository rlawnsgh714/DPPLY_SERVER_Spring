package com.stuent.dpply.api.posting.service;

import com.stuent.dpply.api.auth.domain.entity.User;
import com.stuent.dpply.api.auth.domain.enums.UserRole;
import com.stuent.dpply.api.posting.domain.dto.CreatePostDto;
import com.stuent.dpply.api.posting.domain.dto.ModifyPostDto;
import com.stuent.dpply.api.posting.domain.entity.Posting;
import com.stuent.dpply.api.posting.domain.enums.PostingStatus;
import com.stuent.dpply.api.posting.domain.repository.PostingRepository;
import com.stuent.dpply.common.exception.ForbiddenException;
import com.stuent.dpply.common.exception.NotFoundException;
import com.stuent.dpply.common.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public void modifyPost(User user, ModifyPostDto dto) {
        Posting posting = postingRepository.findById(dto.getPostingId())
                .orElseThrow(() -> new NotFoundException("해당 게시물은 존재하지 않습니다"));
        if(!(posting.getUser().equals(user))) {
            throw new UnauthorizedException("다른 사람의 게시물은 수정할 수 없습니다");
        }
        posting.updatePosting(dto.getText());
        postingRepository.save(posting);
    }

    @Override
    @Transactional
    public void deletePost(User user, int id) {
        Posting posting = postingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 게시물은 존재하지 않습니다"));
        if(!(posting.getUser().equals(user)) && !(user.getRole().equals(UserRole.ADMIN))) {
            throw new UnauthorizedException("다른 사람의 게시물은 삭제할 수 없습니다");
        }
        postingRepository.delete(posting);
    }

    @Override
    public void soledPost(User user, int id) {
        Posting posting = postingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 게시물은 존재하지 않습니다"));
        posting.updateStatus(PostingStatus.SOLVED);
        postingRepository.save(posting);
    }
}
