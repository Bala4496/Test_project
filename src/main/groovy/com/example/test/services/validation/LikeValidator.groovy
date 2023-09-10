package com.example.test.services.validation

import com.example.test.model.Like
import com.example.test.services.PostService
import com.example.test.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.server.ResponseStatusException

@Component
class LikeValidator implements Validator<Like> {

    UserService userService
    PostService postService

    LikeValidator(UserService userService,
                  PostService postService) {
        this.userService = userService
        this.postService = postService
    }

    @Override
    void validate(Like entity) {
        validateUser(entity.getUserId())
        validatePost(entity.getPostId())
    }

    private void validateUser(String userId) {
        if (!StringUtils.hasText(userId) || !userService.existUserById(userId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with ID: ${userId} not found")
        }
    }

    private void validatePost(String postId) {
        if (!StringUtils.hasText(postId) || !postService.existPostById(postId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post with ID: ${postId} not found")
        }
    }

    @Override
    boolean support(Object entity) {
        entity instanceof Like
    }
}
