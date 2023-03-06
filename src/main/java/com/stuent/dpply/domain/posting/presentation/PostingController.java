package com.stuent.dpply.domain.posting.presentation;

import com.stuent.dpply.domain.auth.entity.User;
import com.stuent.dpply.common.enums.UserRole;
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
import com.stuent.dpply.domain.posting.service.PostingService;
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
            @RequestParam(name = "sort", required = false) SortMethod sort
    ) {
        List<Posting> postingList = postingService.getPostByStatusAndSort(status, sort);
        return new ResponseData<>(
                HttpStatus.OK,
                "대기중인 게시물 조회 성공",
                postingList
        );
    }

    @GetMapping("/page")
    public ResponseData<PostingPaginationResponse> getPostByPage(
            @RequestParam(name = "page") int page
    ) {
        PostingPaginationResponse postingList = postingService.getPostByPage(page);
        return new ResponseData<>(
                HttpStatus.OK,
                "대기중인 게시물 조회 성공",
                postingList
        );
    }

    @CheckAuthorization
    @GetMapping("/my")
    public ResponseData<List<Posting>> getMyPost(
            @RequestAttribute User user,
            @RequestParam(value = "type", required = false) PostingStatus status,
            @RequestParam(value = "tag", required = false) PostingTag tag
    ) {
        List<Posting> postingList = postingService.getMyPost(user, status, tag);
        return new ResponseData<>(
                HttpStatus.OK,
                "내 게시물 가져오기 성공",
                postingList
        );
    }

    @GetMapping("/tag")
    public ResponseData<List<Posting>> getPostByTag(
            @RequestParam("tag") PostingTag tag
    ) {
        List<Posting>  postingList = postingService.getPostByTag(tag);
        return new ResponseData<>(
                HttpStatus.OK,
                "태그별 게시물 가져오기 성공",
                postingList
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
            @RequestBody @Valid CreatePostRequest dto
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
            @RequestBody ModifyPostRequest dto
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

    @CheckAuthorization
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
            @RequestBody @Valid CreateCommentRequest dto
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
            @RequestBody ModifyCommentRequest dto
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
