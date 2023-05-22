package com.euije.Simple.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Post {
    private int id;
    private String title;
    private String contents;
    private String user;
    private LocalDateTime createdAt;
    private LocalDateTime editedAt;
}
