package com.example.test.services.validation

import com.example.test.model.User
import com.example.test.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.server.ResponseStatusException

@Component
class UserValidator implements Validator<User> {

    UserService userService

    UserValidator(UserService userService) {
        this.userService = userService
    }

    @Override
    void validate(User entity) {
        validateUsername(entity.getUsername())
        validateEmail(entity.getEmail())
    }

    private void validateUsername(String username) {
        if (!StringUtils.hasText(username)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is empty")
        }
        if (userService.existUserByUsername(username)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with username '${username}' already exist")
        }
    }

    private void validateEmail(String email) {
        if (!StringUtils.hasText(email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is empty")
        }
        if (userService.existUserByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with email '${email}' already exist")
        }
    }

    @Override
    boolean support(Object entity) {
        entity instanceof User
    }
}
