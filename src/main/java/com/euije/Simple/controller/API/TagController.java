package com.euije.Simple.controller.API;

import com.euije.Simple.common.dto.APIResponse;
import com.euije.Simple.common.dto.SuccessStatus;
import com.euije.Simple.controller.dto.response.TagAllResponseDto;
import com.euije.Simple.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tag")
public class TagController {

    private final TagService tagService;

    @GetMapping
    public APIResponse<List<TagAllResponseDto>> getTagList() {
        return APIResponse.success(SuccessStatus.READ_TAG_ALL_SUCCESS, tagService.readTagAll());
    }
}