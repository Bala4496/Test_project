package com.example.test.dto

import java.time.LocalDateTime

class NewsfeedPostDTO {
    String id
    String authorId
    String title
    List<LikeDTO> likes
    List<CommentDTO> comments
    LocalDateTime createdAt
    LocalDateTime updatedAt
}
