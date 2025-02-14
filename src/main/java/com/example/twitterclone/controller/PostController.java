package com.example.twitterclone.controller;

import com.example.twitterclone.model.Post;
import com.example.twitterclone.model.User;
import com.example.twitterclone.service.PostService;
import com.example.twitterclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping
    public Post createPost(@RequestBody Post post, @RequestParam String username) {
        User user = userService.findByUsername(username).orElse(null);
        if (user != null) {
            post.setUser (user);
            return postService.savePost(post);
        }
        return null;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.findAllPosts();
    }

    @GetMapping("/user/{username}")
    public List<Post> getPostsByUser (@PathVariable String username) {
        User user = userService.findByUsername(username).orElse(null);
        if (user != null) {
            return postService.findPostsByUser (user);
        }
        return null;
    }
}