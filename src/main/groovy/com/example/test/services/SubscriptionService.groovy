package com.example.test.services

import com.example.test.model.Subscription
import com.example.test.model.User

interface SubscriptionService {

    List<Subscription> getSubscription()
    List<User> getFollowers(String followingId)
    List<User> getFollowings(String followerId)
    void subscribe(Subscription subscription)
    void unsubscribe(Subscription subscription)
}