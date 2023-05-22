package com.euije.Simple.controller.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TagAllResponseDto implements Comparable<TagAllResponseDto> {
    private int id;
    private String name;
    private int postCount;

    @Override
    public int compareTo(TagAllResponseDto o) {
        if(this.postCount < o.postCount) {
            return 1;
        }
        else if(this.postCount == o.postCount) {
            return 0;
        }
        else {
            return -1;
        }
    }
}
