package com.oskarro.codecity.service;

import com.oskarro.codecity.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
}
