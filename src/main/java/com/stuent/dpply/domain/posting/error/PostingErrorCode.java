package com.stuent.dpply.domain.posting.error;

import com.stuent.dpply.common.exception.error.ErrorProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PostingErrorCode implements ErrorProperty {


    NOT_CTRL_POST(403, "다른 사람의 게시물은 수정/삭제할 수 없습니다"),
    POST_NOT_FOUND(404, "해당 게시물이 존재하지 않습니다"),
    POST_COUNT_NOT_FOUND(404, "건의함 갯수으로 정한 데이터가 비었습니다"),
    POSTING_SYMPATHY_NOT_FOUND(404, "공감 표시를 하지 않았습니다"),
    DO_NOT_POST(403, "한 달간 정해진 갯수 이상의 건의를 할 수 없습니다")
    ;

    private final int status;
    private final String message;
}