package com.example.test.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

import java.time.LocalDateTime

@Document(collection = "subscriptions")
class Subscription {
    @Id
    String id
    @Indexed
    String followerId
    @Indexed
    String followingId
    LocalDateTime createdAt
}
