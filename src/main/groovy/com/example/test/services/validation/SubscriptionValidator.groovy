package com.example.test.services.validation


import com.example.test.model.Subscription
import com.example.test.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.server.ResponseStatusException

@Component
class SubscriptionValidator implements Validator<Subscription> {

    UserService userService

    SubscriptionValidator(UserService userService) {
        this.userService = userService
    }

    @Override
    void validate(Subscription entity) {
        def followingId = entity.getFollowingId()
        def followerId = entity.getFollowerId()
        validateUserId(followingId)
        validateUserId(followerId)
        validateSubscriptionIds(followerId, followingId)
    }

    private void validateUserId(String userId) {
        if (!StringUtils.hasText(userId) || !userService.existUserById(userId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User ID: ${userId} not exist")
        }
    }

    private void validateSubscriptionIds(String followerId, String followingId) {
        if (Objects.equals(followingId, followerId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User ID: ${followingId} cannot be subscribed to himself")
        }
    }

    @Override
    boolean support(Object entity) {
        entity instanceof Subscription
    }
}
