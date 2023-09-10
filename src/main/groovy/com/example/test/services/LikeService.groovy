package com.example.test.services

import com.example.test.model.Like

interface LikeService {
    void like(Like like)
    void unlike(Like like)
    List<Like> getLikesByPostId(String postId)
}