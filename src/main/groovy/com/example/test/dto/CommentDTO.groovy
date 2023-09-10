package com.example.test.dto

import java.time.LocalDateTime

class CommentDTO {
    String id
    String message
    String authorId
    String postId
    LocalDateTime createdAt
    LocalDateTime updatedAt
}
