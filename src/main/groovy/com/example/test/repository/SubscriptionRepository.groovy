package com.example.test.repository

import com.example.test.model.Subscription
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SubscriptionRepository extends MongoRepository<Subscription, String> {
    List<Subscription> findByFollowerId(String followerId);
    List<Subscription> findByFollowingId(String followingId);
    Optional<Subscription> findByFollowerIdAndFollowingId(String followerId,  String followingId);
}