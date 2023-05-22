package com.euije.Simple.controller.view;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class BlogController {

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String viewIndex() {
        return "index";
    }

    @GetMapping("/post/{post_id}")
    @ResponseStatus(HttpStatus.OK)
    public String viewPost(@PathVariable("post_id") int postId, Model model) {
        model.addAttribute("post_id", postId);
        return "post";
    }

    @GetMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    public String viewUpload() {
        return "upload";
    }

    @GetMapping("/edit/{post_id}")
    @ResponseStatus(HttpStatus.OK)
    public String viewEdit(@PathVariable("post_id") int postId, Model model) {
        model.addAttribute("post_id", String.valueOf(postId));
        return "edit";
    }
}
