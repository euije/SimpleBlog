package com.euije.Simple.service;

import com.euije.Simple.controller.dto.response.PostTagAllResponseDto;
import com.euije.Simple.controller.dto.response.TagAllResponseDto;
import com.euije.Simple.domain.Tag;
import com.euije.Simple.infrastructure.PostRepository;
import com.euije.Simple.infrastructure.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor

public class TagService {

    private final TagRepository tagRepository;
    private final PostRepository postRepository;

    public List<PostTagAllResponseDto> readPostTagAll(int postId) {
        List<PostTagAllResponseDto> result = new ArrayList<>();

        for(Tag tag : tagRepository.findAllByPostId(postId)) {
            result.add(PostTagAllResponseDto.builder()
                            .id(tag.getId())
                            .name(tag.getName())
                            .build());
        }

        if(result.isEmpty()) return Collections.emptyList();

        return result;
    }

    public List<TagAllResponseDto> readTagAll(){
        List<TagAllResponseDto> result = new ArrayList<>();

        List<Tag> tags = tagRepository.findAll();
        for(Tag tag : tags) {
            int count = postRepository.countByTagId(tag.getId());

            result.add(TagAllResponseDto.builder()
                            .id(tag.getId())
                            .name(tag.getName())
                            .postCount(count)
                            .build());
        }

        if(result.isEmpty()) return Collections.emptyList();

        result.sort(TagAllResponseDto::compareTo);

        return result;
    }

}
