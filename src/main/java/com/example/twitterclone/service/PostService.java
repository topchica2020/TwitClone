package com.example.twitterclone.service;

import com.example.twitterclone.model.Post;
import com.example.twitterclone.model.User;
import com.example.twitterclone.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> findPostsByUser (User user) {
        return postRepository.findByUser (user);
    }
}