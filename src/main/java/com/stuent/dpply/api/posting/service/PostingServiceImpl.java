package com.stuent.dpply.api.posting.service;

import com.stuent.dpply.api.auth.domain.entity.User;
import com.stuent.dpply.api.auth.domain.enums.UserRole;
import com.stuent.dpply.api.posting.domain.dto.CreatePostDto;
import com.stuent.dpply.api.posting.domain.dto.ModifyPostDto;
import com.stuent.dpply.api.posting.domain.entity.Posting;
import com.stuent.dpply.api.posting.domain.entity.PostingSympathy;
import com.stuent.dpply.api.posting.domain.enums.SortMethod;
import com.stuent.dpply.api.posting.domain.enums.PostingStatus;
import com.stuent.dpply.api.posting.domain.enums.PostingSympathyStatus;
import com.stuent.dpply.api.posting.domain.repository.PostingCountRepository;
import com.stuent.dpply.api.posting.domain.repository.PostingRepository;
import com.stuent.dpply.api.posting.domain.repository.PostingSympathyRepository;
import com.stuent.dpply.common.exception.ForbiddenException;
import com.stuent.dpply.common.exception.NotFoundException;
import com.stuent.dpply.common.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostingServiceImpl implements PostingService{

    private final PostingRepository postingRepository;
    private final PostingSympathyRepository postingSympathyRepository;
    private final PostingCountRepository postingCountRepository;

    @Override
    public List<Posting> getPostByStatusAndSort(PostingStatus status, SortMethod sort) {
        return postingRepository.findByStatus(status);
    }

    @Override
    public void createPost(User user, CreatePostDto dto) {
        LocalDate now = LocalDate.now();
        int postingCount = postingRepository.countByUserAndCreateAtBetween(user, now.minusMonths(1), now);

        int count = postingCountRepository.findById(1L)
                .orElseThrow(() -> new NotFoundException("건의함 갯수으로 정한 데이터가 비었습니다")).getCount();
        if(postingCount >= count) {
            throw new ForbiddenException("한 달간 "+ count + "개 이상의 건의를 넣을 수 없습니다");
        }
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
        posting.updateDate(LocalDate.now());
        postingRepository.save(posting);
    }

    @Override
    @Transactional
    public void deletePost(User user, Long id) {
        Posting posting = postingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 게시물은 존재하지 않습니다"));
        if(!(posting.getUser().equals(user)) && !(user.getRole().equals(UserRole.ADMIN))) {
            throw new UnauthorizedException("다른 사람의 게시물은 삭제할 수 없습니다");
        }
        postingRepository.delete(posting);
    }

    @Override
    public void soledPost(Long id) {
        Posting posting = postingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 게시물은 존재하지 않습니다"));
        posting.updateStatus(PostingStatus.SOLVED);
        postingRepository.save(posting);
    }

    @Override
    public void refusePost(Long id) {
        Posting posting = postingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 게시물은 존재하지 않습니다"));
        posting.updateStatus(PostingStatus.REFUSED);
        postingRepository.save(posting);
    }

    @Override
    public void signSympathy(User user, Long id) {
        Posting posting = postingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 게시물은 존재하지 않습니다"));
        PostingSympathy postingSympathy = postingSympathyRepository.findByUserAndPosting(user, posting)
                .orElseGet(() -> PostingSympathy.builder()
                        .posting(posting)
                        .user(user)
                        .build());
        postingSympathy.updateStatus(PostingSympathyStatus.YES);
        postingSympathyRepository.save(postingSympathy);
    }

    @Override
    public void cancelSympathy(User user, Long id) {
        Posting posting = postingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 게시물은 존재하지 않습니다"));
        PostingSympathy postingSympathy = postingSympathyRepository.findByUserAndPosting(user, posting)
                .orElseThrow(() -> new NotFoundException("공감 표시를 하지 않았습니다"));
        postingSympathy.updateStatus(PostingSympathyStatus.NO);
        postingSympathyRepository.save(postingSympathy);
    }
}
