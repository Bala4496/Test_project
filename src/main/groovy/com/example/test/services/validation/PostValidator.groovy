package com.example.test.services.validation

import com.example.test.model.Post
import com.example.test.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.server.ResponseStatusException

@Component
class PostValidator implements Validator<Post> {

    UserService userService

    PostValidator(UserService userService) {
        this.userService = userService
    }

    @Override
    void validate(Post entity) {
        validateTitle(entity.getTitle())
        validateAuthor(entity.getAuthorId())
    }

    private void validateTitle(String title) {
        if (!StringUtils.hasText(title)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Text specified in post is empty")
        }
    }

    private void validateAuthor(String authorId) {
        if (!userService.existUserById(authorId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User specified in post not found")
        }
    }

    @Override
    boolean support(Object entity) {
        entity instanceof Post
    }
}
