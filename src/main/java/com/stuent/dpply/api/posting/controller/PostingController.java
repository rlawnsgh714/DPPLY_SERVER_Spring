package com.stuent.dpply.api.posting.controller;

import com.stuent.dpply.api.posting.domain.entity.Posting;
import com.stuent.dpply.api.posting.service.PostingService;
import com.stuent.dpply.common.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posting")
@RequiredArgsConstructor
public class PostingController {

    private final PostingService postingService;

    @GetMapping("/waiting")
    public ResponseData<List<Posting>> getWaitingPost() {
        List<Posting> postingList = postingService.getWaitingPost();
        return new ResponseData<>(
                HttpStatus.OK,
                "대기중인 게시물 조회 성공",
                postingList
        );
    }
}
