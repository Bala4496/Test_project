package com.example.test.controller

import com.example.test.dto.SubscriptionDTO
import com.example.test.dto.UserDTO
import com.example.test.mapper.SubscriptionMapper
import com.example.test.mapper.UserMapper
import com.example.test.model.Subscription
import com.example.test.services.SubscriptionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/subscription")
class SubscriptionControllerV1 {

    SubscriptionService subscriptionService
    SubscriptionMapper subscriptionMapper
    UserMapper userMapper

    @Autowired
    UserController(SubscriptionService subscriptionService,
                   SubscriptionMapper subscriptionMapper,
                   UserMapper userMapper) {
        this.subscriptionService = subscriptionService
        this.subscriptionMapper = subscriptionMapper
        this.userMapper = userMapper
    }

    @GetMapping("/")
    List<SubscriptionDTO> getSubscription() {
        subscriptionService.getSubscription()
                .collect {subscription -> subscriptionMapper.map(subscription) }
    }

    @PostMapping("/subscribe")
    void addLike(@RequestBody Subscription subscription) {
        subscriptionService.subscribe(subscription)
    }

    @PostMapping("/unsubscribe")
    void removeLike(@RequestBody Subscription subscription) {
        subscriptionService.unsubscribe(subscription)
    }

    @GetMapping("/followers/{id}")
    List<UserDTO> getFollowers(@PathVariable String id) {
        subscriptionService.getFollowers(id)
                .collect { user -> userMapper.map(user) }
    }

    @GetMapping("/followings/{id}")
    List<UserDTO> getFollowings(@PathVariable String id) {
        subscriptionService.getFollowings(id)
                .collect { user -> userMapper.map(user) }
    }
}
