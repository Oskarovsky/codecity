package com.oskarro.codecity.controllers;

import com.oskarro.codecity.config.CustomUserDetails;
import com.oskarro.codecity.entities.Post;
import com.oskarro.codecity.service.CoderService;
import com.oskarro.codecity.service.PostService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class BlogController {

    private PostService postService;
    private CoderService coderService;

    public BlogController(PostService postService, CoderService coderService) {
        this.postService = postService;
        this.coderService = coderService;
    }

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/posts")
    public List<Post> posts() {
        return postService.getAllPosts();
    }

    @PostMapping(value = "/post")
    public void publishPost(@RequestBody Post post) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(post.getDateCreated() == null) {
            post.setDateCreated(new Date());
        }
        post.setCreator(coderService.getCoder(userDetails.getUsername()));
        postService.addPost(post);
    }
}
