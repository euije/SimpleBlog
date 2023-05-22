package com.euije.Simple.controller.API;

import com.euije.Simple.common.dto.APIResponse;
import com.euije.Simple.common.dto.SuccessStatus;
import com.euije.Simple.controller.dto.request.PostCreateRequestDto;
import com.euije.Simple.controller.dto.request.PostUpdateRequestDto;
import com.euije.Simple.controller.dto.response.PostAllResponseDto;
import com.euije.Simple.controller.dto.response.PostDetailResponseDto;
import com.euije.Simple.controller.dto.response.PostTagAllResponseDto;
import com.euije.Simple.service.PostService;
import com.euije.Simple.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;
    private final TagService tagService;

    @GetMapping
    public APIResponse<List<PostAllResponseDto>> getPostList() {
        return APIResponse.success(SuccessStatus.READ_POST_SUCCESS, postService.readPostAll());
    }

    @GetMapping("/{post_id}")
    public APIResponse<PostDetailResponseDto> getPostDetail(@PathVariable("post_id") int postId) {
        return APIResponse.success(SuccessStatus.READ_POST_DETAIL_SUCCESS, postService.readPostDetail(postId));
    }

    @GetMapping("/{post_id}/tag")
    public APIResponse<List<PostTagAllResponseDto>> getTagList(@PathVariable("post_id") int postId) {
        return APIResponse.success(SuccessStatus.READ_TAG_SUCCESS, tagService.readPostTagAll(postId));
    }

    @PostMapping
    public APIResponse<String> postPost(@RequestBody final PostCreateRequestDto request) {
        System.out.println("request = " + request);
        return APIResponse.success(SuccessStatus.WRITE_POST_SUCCESS, postService.createPost(request));
    }

    @PutMapping("/{post_id}")
    public APIResponse<String> putPost(@PathVariable("post_id") int postId, @RequestBody final PostUpdateRequestDto request) {
        return APIResponse.success(SuccessStatus.EDIT_POST_SUCCESS, postService.updatePost(postId, request));
    }

    @DeleteMapping("/{post_id}")
    public APIResponse<String> deletePost(@PathVariable("post_id") int postId) {
        return APIResponse.success(SuccessStatus.REMOVE_POST_SUCCESS, postService.deletePost(postId));
    }
}
