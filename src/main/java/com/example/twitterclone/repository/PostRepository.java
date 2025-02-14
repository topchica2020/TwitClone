package com.example.twitterclone.repository;

import com.example.twitterclone.model.Post;
import com.example.twitterclone.model.User;
import com.example.twitterclone.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    // Метод для поиска постов по пользователю
    List<Post> findByUser(User user);

    // Метод для получения поста с данными пользователя и категории
    @Query("SELECT p FROM Post p " +
            "JOIN FETCH p.user u " +
            "JOIN FETCH p.category c " +
            "WHERE p.id = :postId")
    Optional<Post> findPostWithUserAndCategory(Long postId);

    // Метод для получения всех постов с категорией
    @Query("SELECT p FROM Post p " +
            "JOIN FETCH p.category c")
    List<Post> findAllPostsWithCategory();

    // Метод для поиска постов по категории
    List<Post> findByCategory_Name(String categoryName);
}
