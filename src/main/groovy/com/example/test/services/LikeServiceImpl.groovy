package com.example.test.services

import com.example.test.model.Like
import com.example.test.repository.LikeRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class LikeServiceImpl implements LikeService {

    LikeRepository likeRepository

    LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository
    }

    @Override
    void like(Like like) {
        def postId = like.getPostId()
        def userId = like.getUserId()
        likeRepository.findLikeByPostIdAndUserId(postId, userId).ifPresentOrElse(
                storedLike -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Like on post ID: ${storedLike.getPostId()} already present")
                },
                () -> likeRepository.save(new Like(postId: postId, userId: userId))
        )
    }

    @Override
    void unlike(Like like) {
        def postId = like.getPostId()
        def userId = like.getUserId()
        likeRepository.findLikeByPostIdAndUserId(postId, userId).ifPresentOrElse(
                likeRepository::delete,
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Like on post ID: ${postId} not present")
                }
        )
    }

    @Override
    List<Like> getLikesByPostId(String postId) {
        likeRepository.findLikesByPostId(postId)
    }
}
