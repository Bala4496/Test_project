package com.example.test.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

import java.time.LocalDateTime

@Document(collection = "posts")
class Post {
    @Id
    String id
    String authorId
    String title
    List<Like> likes
    List<Comment> comments
    LocalDateTime createdAt
    LocalDateTime updatedAt

    @Override
    String toString() {
        return title + " " + authorId
    }
}
