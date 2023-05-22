package com.euije.Simple.controller.dto.response;

import com.euije.Simple.domain.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostDetailResponseDto {
    private int id;
    private String title;
    private String contents;
    private String user;
    private LocalDateTime createdAt;
    private LocalDateTime editedAt;
}
