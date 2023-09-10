package com.example.test.repository

import com.example.test.model.Like
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface LikeRepository extends MongoRepository<Like, String> {
    List<Like> findLikesByPostId(String postId)
    Optional<Like> findLikeByPostIdAndUserId(String postId, String userId)
}
