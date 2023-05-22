package com.euije.Simple.controller.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostAllResponseDto {
    private long id;
    private String title;
    private String user;
    private LocalDateTime createdAt;
}
