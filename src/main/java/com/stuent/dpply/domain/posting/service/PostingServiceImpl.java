package com.stuent.dpply.domain.posting.service;

import com.stuent.dpply.domain.auth.entity.User;
import com.stuent.dpply.common.enums.UserRole;
import com.stuent.dpply.domain.posting.presentation.dto.request.CreateCommentRequest;
import com.stuent.dpply.domain.posting.presentation.dto.request.CreatePostRequest;
import com.stuent.dpply.domain.posting.presentation.dto.request.ModifyCommentRequest;
import com.stuent.dpply.domain.posting.presentation.dto.request.ModifyPostRequest;
import com.stuent.dpply.domain.posting.entity.Posting;
import com.stuent.dpply.domain.posting.entity.PostingComment;
import com.stuent.dpply.domain.posting.entity.PostingSympathy;
import com.stuent.dpply.common.enums.PostingTag;
import com.stuent.dpply.common.enums.SortMethod;
import com.stuent.dpply.common.enums.PostingStatus;
import com.stuent.dpply.common.enums.PostingSympathyStatus;
import com.stuent.dpply.domain.posting.entity.repository.PostingCommentRepository;
import com.stuent.dpply.domain.posting.entity.repository.PostingCountRepository;
import com.stuent.dpply.domain.posting.entity.repository.PostingRepository;
import com.stuent.dpply.domain.posting.entity.repository.PostingSympathyRepository;
import com.stuent.dpply.domain.posting.exception.*;
import com.stuent.dpply.domain.posting.presentation.dto.response.PostingPaginationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostingServiceImpl implements PostingService{

    private final PostingRepository postingRepository;
    private final PostingSympathyRepository postingSympathyRepository;
    private final PostingCountRepository postingCountRepository;
    private final PostingCommentRepository postingCommentRepository;

    @Override
    public List<Posting> getPostByStatusAndSort(PostingStatus status, SortMethod sort) {
        if (sort != null) {
            if (sort.equals(SortMethod.SYMPATHY)) {
                return postingRepository.findByStatusOrderBySympathyCount(status);
            }
        }
        return postingRepository.findByStatusOrderByCreateAt(status);
    }

    @Override
    public PostingPaginationResponse getPostByPage(int page) {
        Pageable pageRequest = PageRequest.of(page - 1, 10);
        long postingCount = postingRepository.count(); //2
        long pageCount = 1;
        for (long i = 1; i < postingCount; i++) {
            if (i * 10 >= postingCount) {
                pageCount = i;
                break;
            }
        }
        return new PostingPaginationResponse(
                postingRepository.findAllByStatus(PostingStatus.WAITING, pageRequest), pageCount);
    }

    @Override
    public List<Posting> getMyPost(User user, PostingStatus status, PostingTag tag) {
        if (status != null && tag != null) {
            return postingRepository.findByUserAndStatusAndTag(user, status, tag);
        } else if (tag != null) {
            return postingRepository.findByUserAndTag(user, tag);
        } else if (status != null) {
            return postingRepository.findByUserAndStatus(user, status);
        } else {
            return postingRepository.findByUser(user);
        }
    }

    @Override
    public List<Posting> getPostByTag(PostingTag tag) {
        return postingRepository.findByTag(tag);
    }

    @Override
    public Posting getPostById(Long id) {
        return postingRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
    }

    @Override
    public void createPost(User user, CreatePostRequest dto) {
        LocalDate now = LocalDate.now();
        int postingCount = postingRepository.countByUserAndCreateAtBetween(user, now.minusMonths(1), now);

        int count = postingCountRepository.findById(1L)
                .orElseThrow(() -> PostCountNotFoundException.EXCEPTION).getCount();
        if(postingCount >= count) {
            throw DoNotPostException.EXCEPTION;
        }
        Posting posting = Posting.builder()
                .title(dto.getTitle())
                .tag(dto.getTag())
                .text(dto.getText())
                .user(user)
                .imageUrl(dto.getImageUrl())
                .build();
        postingRepository.save(posting);
    }

    @Override
    public void modifyPost(User user, ModifyPostRequest dto) {
        Posting posting = postingRepository.findById(dto.getPostingId())
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        if(!(posting.getUser().equals(user))) {
            throw NotCtrlPostException.EXCEPTION;
        }
        posting.updatePosting(dto.getTitle(), dto.getText(), posting.getStatus(), LocalDate.now());
        postingRepository.save(posting);
    }

    @Override
    @Transactional
    public void deletePost(User user, Long id) {
        Posting posting = postingRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        if(posting.getUser().getUniqueId().equals(user.getUniqueId()) || user.getRole().equals(UserRole.ADMIN)) {
            postingCommentRepository.deleteByPosting(posting);
            postingSympathyRepository.deleteByPosting(posting);
            postingRepository.delete(posting);
            return;
        }
        throw NotCtrlPostException.EXCEPTION;
    }

    @Override
    public void solvedPost(Long id) {
        Posting posting = postingRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        posting.updatePosting(posting.getTitle(), posting.getText(), PostingStatus.SOLVED, LocalDate.now());
        postingRepository.save(posting);
    }

    @Override
    public void refusePost(Long id) {
        Posting posting = postingRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        posting.updatePosting(posting.getTitle(), posting.getText(), PostingStatus.REFUSED,LocalDate.now());
        postingRepository.save(posting);
    }

    @Override
    public void signSympathy(User user, Long id) {
        Posting posting = postingRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        PostingSympathy postingSympathy = postingSympathyRepository.findByUserAndPosting(user, posting)
                .orElseGet(() -> PostingSympathy.builder()
                        .posting(posting)
                        .user(user)
                        .build());
        postingSympathy.updateStatus(PostingSympathyStatus.YES);
        if (postingSympathyRepository.existsByUserAndPostingAndStatus(user, posting, PostingSympathyStatus.YES)) {
            throw AlreadyPostingSympathyException.EXCEPTION;
        }
        posting.updateSympathyCount(posting.getSympathyCount() + 1);
        postingSympathyRepository.save(postingSympathy);
        postingRepository.save(posting);
    }

    @Override
    public void cancelSympathy(User user, Long id) {
        Posting posting = postingRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        if (postingSympathyRepository.existsByUserAndPostingAndStatus(user, posting, PostingSympathyStatus.NO)) {
            throw AlreadyPostingNotSympathyException.EXCEPTION;
        }
        if (posting.getSympathyCount() - 1 >= 0) {
            posting.updateSympathyCount(posting.getSympathyCount() - 1);
        }
        PostingSympathy postingSympathy = postingSympathyRepository.findByUserAndPosting(user, posting)
                .orElseThrow(() -> PostingSympathyNotFoundException.EXCEPTION);
        postingSympathy.updateStatus(PostingSympathyStatus.NO);
        postingSympathyRepository.save(postingSympathy);
        postingRepository.save(posting);
    }

    @Override
    public List<PostingComment> getPostingComment(Long id) {
        Posting posting = postingRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        return postingCommentRepository.findByPosting(posting);
    }

    @Override
    public void createComment(User user, Long id, CreateCommentRequest dto) {
        Posting posting = postingRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        PostingComment comment = PostingComment.builder()
                .user(user)
                .posting(posting)
                .comment(dto.getComment())
                .build();
        postingCommentRepository.save(comment);
    }

    @Override
    public void modifyComment(User user, Long id, ModifyCommentRequest dto) {
        PostingComment postingComment = postingCommentRepository.findById(id)
                        .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        if(!user.equals(postingComment.getUser())) {
            throw NotCtrlPostException.EXCEPTION;
        }
        postingComment.modifyComment(dto.getComment());
        postingCommentRepository.save(postingComment);
    }

    @Override
    public void deleteComment(User user, Long id) {
        PostingComment postingComment = postingCommentRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        if(!user.equals(postingComment.getUser())) {
            throw NotCtrlPostException.EXCEPTION;
        }
        postingCommentRepository.delete(postingComment);
    }
}
