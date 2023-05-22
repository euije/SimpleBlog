package com.euije.Simple.controller.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostUpdateRequestDto {
    @Nullable
    private String title;
    @Nullable
    private String contents;
    @Nullable
    private String user;
}
