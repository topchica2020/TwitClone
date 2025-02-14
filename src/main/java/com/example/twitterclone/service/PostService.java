package com.example.twitterclone.service;

import com.example.twitterclone.model.Post;
import com.example.twitterclone.model.User;
import com.example.twitterclone.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    /**
     * Сохраняем пост
     */
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    /**
     * Находим все посты
     */
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    /**
     * Находим посты пользователя
     */
    public List<Post> findPostsByUser(User user) {
        return postRepository.findByUser(user);
    }

    /**
     * Получаем подробную информацию о посте, включая пользователя и категорию
     */
    public Post getFullPostDescription(Long postId) {
        Optional<Post> post = postRepository.findPostWithUserAndCategory(postId);
        return post.orElseThrow(() -> new RuntimeException("Post not found"));
    }

    /**
     * Фильтруем посты по категории
     */
    public List<Post> filterPostsByCategory(List<Post> posts, String categoryName) {
        return posts.stream()
                .filter(post -> post.getCategory() != null && post.getCategory().getName().equals(categoryName))
                .collect(Collectors.toList());
    }

    /**
     * Получаем все посты, отсортированные по контенту
     */
    public List<Post> getPostsSortedByContent() {
        return postRepository.findAll().stream()
                .sorted((p1, p2) -> p1.getContent().compareTo(p2.getContent()))
                .collect(Collectors.toList());
    }
}
