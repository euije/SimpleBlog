package com.euije.Simple.service;

import com.euije.Simple.common.dto.Error;
import com.euije.Simple.controller.dto.request.PostCreateRequestDto;
import com.euije.Simple.controller.dto.request.PostUpdateRequestDto;
import com.euije.Simple.controller.dto.response.PostAllResponseDto;
import com.euije.Simple.controller.dto.response.PostDetailResponseDto;
import com.euije.Simple.domain.Post;
import com.euije.Simple.domain.Tag;
import com.euije.Simple.exception.model.NotFoundException;
import com.euije.Simple.infrastructure.PostRepository;
import com.euije.Simple.infrastructure.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;

    public List<PostAllResponseDto> readPostAll() {
        List<Post> posts = postRepository.findAll();
        List<PostAllResponseDto> result = new ArrayList<>();

        for(Post post : posts) {
            result.add(PostAllResponseDto.builder()
                            .id(post.getId())
                            .title(post.getTitle())
                            .user(post.getUser())
                            .createdAt(post.getCreatedAt())
                            .build());
        }

        if(result.isEmpty()) return Collections.emptyList();

        return result;
    }

    public PostDetailResponseDto readPostDetail(int postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_EXCEPTION, Error.NOT_FOUND_EXCEPTION.getMessage()));

        return PostDetailResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .contents(post.getContents())
                .user(post.getUser())
                .createdAt(post.getCreatedAt())
                .editedAt(post.getEditedAt())
                .build();
    }

    @Transactional(readOnly = false)
    public String createPost(PostCreateRequestDto request) {
        postRepository.save(Post.builder()
                        .title(request.getTitle())
                        .contents(request.getContents())
                        .user(request.getUser())
                        .createdAt(LocalDateTime.now())
                        .editedAt(LocalDateTime.now())
                        .build());
        int id = postRepository.findRecentId();

        for(String name : request.getNewTagNames()) {
            if(name.equals("")) continue;
            tagRepository.save(Tag.builder()
                            .name(name)
                            .build());
        }

        List<Integer> ids = tagRepository.findRecentIdsByCount(request.getNewTagNames().size());
        ids.addAll(request.getOldTagIds());

        tagRepository.saveRelationsWithPostId(id, ids);

        return "id" + "번 게시글이 생성됨";
    }

    public String updatePost(int postId, PostUpdateRequestDto request) {
        Post newPost = Post.builder()
                .title(request.getTitle())
                .contents(request.getContents())
                .user(request.getUser())
                .build();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_EXCEPTION, Error.NOT_FOUND_EXCEPTION.getMessage()));
        postRepository.update(post, newPost);

        return postId + "번 게시글이 수정됨";
    }

    public String deletePost(int postId) {
        postRepository.deleteById(postId);

        return postId + "번 게시글이 삭제됨";
    }
}
