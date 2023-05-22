package com.euije.Simple.controller.dto.request;

import com.euije.Simple.domain.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostCreateRequestDto {
    private String title;
    private String contents;
    private String user;
    private List<String> newTagNames;
    private List<Integer> oldTagIds;
}
