package com.example.twitterclone.repository;

import com.example.twitterclone.model.Post;
import com.example.twitterclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser (User user);
}