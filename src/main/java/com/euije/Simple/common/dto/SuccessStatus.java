package com.euije.Simple.common.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessStatus {
    /*
    post
    */
    READ_POST_SUCCESS(HttpStatus.OK, "모든 게시글 목록 조회에 성공하였습니다"),
    READ_POST_DETAIL_SUCCESS(HttpStatus.OK, "해당 게시글 조회에 성공하였습니다."),
    READ_TAG_SUCCESS(HttpStatus.OK, "해당 게시글의 태그 조회에 성공하였습니다"),
    WRITE_POST_SUCCESS(HttpStatus.CREATED, "게시글 생성에 성공하였습니다"),
    EDIT_POST_SUCCESS(HttpStatus.CREATED, "게시글 수정에 성공하였습니다"),
    REMOVE_POST_SUCCESS(HttpStatus.OK, "해당 게시글 삭제에 성공하였습니다"),
    /*
    tag
    */
    READ_TAG_ALL_SUCCESS(HttpStatus.OK, "모든 태그 목록 조회에 성공하였습니다")

    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}