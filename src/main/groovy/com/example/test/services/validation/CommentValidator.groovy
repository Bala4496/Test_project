package com.example.test.services.validation

import com.example.test.model.Comment
import com.example.test.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.server.ResponseStatusException

@Component
class CommentValidator implements Validator<Comment> {

    UserService userService

    CommentValidator(UserService userService) {
        this.userService = userService
    }

    @Override
    void validate(Comment entity) {
        validateMessage(entity.getMessage())
        validateAuthor(entity.getAuthorId())
    }

    private void validateMessage(String message) {
        if (!StringUtils.hasText(message)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message specified in comment is empty")
        }
    }

    private void validateAuthor(String authorId) {
        if (!userService.existUserById(authorId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User specified in post not found")
        }
    }

    @Override
    boolean support(Object entity) {
        entity instanceof Comment
    }
}
