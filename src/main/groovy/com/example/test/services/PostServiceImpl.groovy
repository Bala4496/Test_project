package com.example.test.services

import com.example.test.model.Like
import com.example.test.model.Post
import com.example.test.repository.PostRepository
import com.example.test.services.validation.ValidatorStrategy
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.server.ResponseStatusException

import java.time.LocalDateTime

@Service
class PostServiceImpl implements PostService {

    private PostRepository postRepository
    private ValidatorStrategy validatorStrategy
    private LikeService likeService

    PostServiceImpl(PostRepository postRepository,
                    ValidatorStrategy validatorStrategy,
                    LikeService likeService) {
        this.postRepository = postRepository
        this.validatorStrategy = validatorStrategy
        this.likeService = likeService
    }

    @Override
    List<Post> getAllPosts() {
        postRepository.findAllByOrderByCreatedAtDesc()
    }

    @Override
    List<Post> getAllPostsByUserId(String userId) {
        postRepository.findPostsByAuthorIdOrderByCreatedAtDesc(userId)
    }

    @Override
    Post getPostById(String id) {
        postRepository.findById(id)
                .orElseThrow { new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found by ID: ${id}") }
    }

    @Override
    boolean existPostById(String id) {
        postRepository.existsById(id)
    }

    @Override
    Post createPost(Post post) {
        validatorStrategy.validate(post)
        def now = LocalDateTime.now()
        post.setCreatedAt(now)
        post.setUpdatedAt(now)
        postRepository.save(post)
    }

    @Override
    Post updatePost(String id, Post updatedUser) {
        postRepository.findById(id).map(post -> {
            replaceUpdatedParams(updatedUser, post)
            post.setUpdatedAt(LocalDateTime.now())
            postRepository.save(post)
        }).orElseThrow { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found with ID: ${id}") }
    }

    @Override
    void deletePost(String id) {
        postRepository.deleteById(id)
    }

    @Override
    void likePost(Like like) {
        validatorStrategy.validate(like)
        likeService.like(like)
    }

    @Override
    void unlikePost(Like like) {
        validatorStrategy.validate(like)
        likeService.unlike(like)
    }

    private void replaceUpdatedParams(Post updatedPost, Post post) {
        if (StringUtils.hasText(updatedPost.getTitle())) {
            post.setTitle(updatedPost.getTitle())
        }
    }
}
