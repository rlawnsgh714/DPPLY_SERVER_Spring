package com.stuent.dpply.api.posting.controller;

import com.stuent.dpply.api.auth.domain.entity.User;
import com.stuent.dpply.api.auth.domain.enums.UserRole;
import com.stuent.dpply.api.posting.domain.dto.CreateCommentDto;
import com.stuent.dpply.api.posting.domain.dto.CreatePostDto;
import com.stuent.dpply.api.posting.domain.dto.ModifyCommentDto;
import com.stuent.dpply.api.posting.domain.dto.ModifyPostDto;
import com.stuent.dpply.api.posting.domain.entity.Posting;
import com.stuent.dpply.api.posting.domain.entity.PostingComment;
import com.stuent.dpply.api.posting.domain.enums.PostingTag;
import com.stuent.dpply.api.posting.domain.enums.SortMethod;
import com.stuent.dpply.api.posting.domain.enums.PostingStatus;
import com.stuent.dpply.api.posting.service.PostingService;
import com.stuent.dpply.common.annotation.CheckAuthorization;
import com.stuent.dpply.common.response.Response;
import com.stuent.dpply.common.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/posting")
@RequiredArgsConstructor
public class PostingController {

    private final PostingService postingService;

    @GetMapping
    public ResponseData<List<Posting>> getPostByStatus(
            @RequestParam(name = "type") PostingStatus status,
            @RequestParam(name = "sort") SortMethod sort
    ) {
        List<Posting> postingList = postingService.getPostByStatusAndSort(status, sort);
        return new ResponseData<>(
                HttpStatus.OK,
                "대기중인 게시물 조회 성공",
                postingList
        );
    }

    @GetMapping("/page")
    public ResponseData<List<Posting>> getPostBypage(
            @RequestParam(name = "page") int page,
            @RequestParam(name = "limit") int limit
    ) {
        List<Posting> postingList = postingService.getPostByPageAndLimit(page, limit);
        return new ResponseData<>(
                HttpStatus.OK,
                "대기중인 게시물 조회 성공",
                postingList
        );
    }

    @GetMapping("/tag")
    public ResponseData<List<Posting>> getPostByTag(
            @RequestParam("tag")PostingTag tag
    ) {
        return new ResponseData<>(
                HttpStatus.OK,
                "",
                null
        );
    }

    @GetMapping("/{id}")
    public ResponseData<Posting> getPostById(
            @PathVariable Long id
    ) {
        Posting posting = postingService.getPostById(id);
        return new ResponseData<>(
                HttpStatus.OK,
                "대기중인 게시물 조회 성공",
                posting
        );
    }

    @CheckAuthorization
    @PostMapping
    public Response createPost(
            @RequestAttribute User user,
            @RequestBody @Valid CreatePostDto dto
    ) {
        postingService.createPost(user, dto);
        return new Response(
                HttpStatus.CREATED,
                "게시물 작성 성공"
        );
    }

    @CheckAuthorization
    @PatchMapping
    public Response modifyPost(
            @RequestAttribute User user,
            @RequestBody ModifyPostDto dto
    ) {
        postingService.modifyPost(user, dto);
        return new Response(
                HttpStatus.OK,
                "게시물 수정 성공"
        );
    }

    @CheckAuthorization
    @DeleteMapping("/{id}")
    public Response deletePost(
            @RequestAttribute User user,
            @PathVariable Long id
    ) {
        postingService.deletePost(user, id);
        return new Response(
                HttpStatus.OK,
                "게시물 삭제 성공"
        );
    }

    @CheckAuthorization(roles = UserRole.ADMIN)
    @PostMapping("/solve/{id}")
    public Response solvePost(
            @PathVariable Long id
    ) {
        postingService.soledPost(id);
        return new Response(
                HttpStatus.CREATED,
                "게시물 해결 처리 성공"
        );
    }

    @CheckAuthorization(roles = UserRole.ADMIN)
    @PostMapping("/refuse/{id}")
    public Response refusePost(
            @PathVariable Long id
    ) {
        postingService.refusePost(id);
        return new Response(
                HttpStatus.CREATED,
                "게시물 기각 처리 성공"
        );
    }

    @PostMapping("/sympathy/{id}")
    public Response signSympathy(
            @RequestAttribute User user,
            @PathVariable Long id
    ) {
        postingService.signSympathy(user, id);
        return new Response(
                HttpStatus.CREATED,
                "공감 표시 성공"
        );
    }

    @CheckAuthorization
    @PostMapping("/sympathy-cancel/{id}")
    public Response cancelSympathy(
            @RequestAttribute User user,
            @PathVariable Long id
    ) {
        postingService.cancelSympathy(user, id);
        return new Response(
                HttpStatus.CREATED,
                "공감 취소 성공"
        );
    }

    @GetMapping("/comment/{postId}")
    public ResponseData<List<PostingComment>> getPostingComment(
            @PathVariable Long postId
    ) {
        List<PostingComment> postingCommentList = postingService.getPostingComment(postId);
        return new ResponseData<>(
                HttpStatus.OK,
                "댓글 가져오기 성공",
                postingCommentList
        );
    }

    @CheckAuthorization
    @PostMapping("/comment/{postId}")
    public Response createComment(
            @RequestAttribute User user,
            @PathVariable Long postId,
            @RequestBody @Valid CreateCommentDto dto
    ) {
        postingService.createComment(user, postId, dto);
        return new Response(
                HttpStatus.OK,
                "댓글 작성 성공"
        );
    }

    @CheckAuthorization
    @PatchMapping("/comment/{commentId}")
    public Response modifyComment(
            @RequestAttribute User user,
            @PathVariable Long commentId,
            @RequestBody ModifyCommentDto dto
    ) {
        postingService.modifyComment(user, commentId, dto);
        return new Response(
                HttpStatus.OK,
                "댓글 수정 성공"
        );
    }

    @CheckAuthorization
    @DeleteMapping("/comment/{commentId}")
    public Response deleteComment(
            @RequestAttribute User user,
            @PathVariable Long commentId
    ) {
        postingService.deleteComment(user, commentId);
        return new Response(
                HttpStatus.OK,
                "댓글 삭제 성공"
        );
    }
}
