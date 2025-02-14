package com.example.twitterclone.controller;

import com.example.twitterclone.model.Post;
import com.example.twitterclone.model.User;
import com.example.twitterclone.model.Category;
import com.example.twitterclone.service.PostService;
import com.example.twitterclone.service.UserService;
import com.example.twitterclone.service.CategoryService; // Сервис для категорий
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Post> createPost(@RequestBody Post post, @RequestParam String username) {
        User user = userService.findByUsername(username).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // Возвращаем статус 404, если пользователь не найден
        }
        post.setUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.savePost(post));
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.findAllPosts();
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable String username) {
        User user = userService.findByUsername(username).orElse(null);
        if (user != null) {
            return ResponseEntity.ok(postService.findPostsByUser(user));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Возвращаем 404, если пользователь не найден
    }
}
