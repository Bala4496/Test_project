package com.example.test.model

import org.springframework.data.mongodb.core.mapping.Document

import java.time.LocalDateTime

@Document(collection = "comments")
class Comment {
    String id
    String message
    String postId
    String authorId
    LocalDateTime createdAt
    LocalDateTime updatedAt
}
