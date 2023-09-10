package com.example.test.services

import com.example.test.model.Subscription
import com.example.test.model.User
import com.example.test.repository.SubscriptionRepository
import com.example.test.services.validation.ValidatorStrategy
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

import java.time.LocalDateTime

@Service
class SubscriptionServiceImpl implements SubscriptionService {

    SubscriptionRepository subscriptionRepository
    ValidatorStrategy validatorStrategy
    UserService userService

    SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,
                            ValidatorStrategy validatorStrategy,
                            UserService userService) {
        this.subscriptionRepository = subscriptionRepository
        this.validatorStrategy = validatorStrategy
        this.userService = userService
    }

    @Override
    List<Subscription> getSubscription() {
        subscriptionRepository.findAll()
    }

    @Override
    List<User> getFollowers(String followingId) {
        subscriptionRepository.findByFollowingId(followingId)
                .collect { subscription -> subscription.getFollowerId() }
                .collect { followerId -> userService.getUserById(followerId) }
    }

    @Override
    List<User> getFollowings(String followerId) {
        subscriptionRepository.findByFollowerId(followerId)
                .collect { subscription -> subscription.getFollowingId() }
                .collect { followingId -> userService.getUserById(followingId) }
    }

    @Override
    void subscribe(Subscription subscription) {
        validatorStrategy.validate(subscription)

        subscriptionRepository.findByFollowerIdAndFollowingId(subscription.getFollowerId(), subscription.getFollowingId()).ifPresentOrElse(
                savedSubscription -> {
                    throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "User ID: ${subscription.getFollowerId()} already following User ID: ${subscription.getFollowingId()}")
                },
                () -> {
                    subscription.setCreatedAt(LocalDateTime.now())
                    subscriptionRepository.save(subscription)
                }
        )
    }

    @Override
    void unsubscribe(Subscription subscription) {
        validatorStrategy.validate(subscription)

        subscriptionRepository.findByFollowerIdAndFollowingId(subscription.getFollowerId(), subscription.getFollowingId()).ifPresentOrElse(
                subscriptionRepository::delete,
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User ID: ${subscription.getFollowingId()} isn't following User ID: ${subscription.getFollowerId()}")
                }
        )
    }
}
