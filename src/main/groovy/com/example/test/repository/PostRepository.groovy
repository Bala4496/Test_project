package com.example.test.repository


import com.example.test.model.Post
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findAllByOrderByCreatedAtDesc()
    List<Post> findPostsByAuthorIdOrderByCreatedAtDesc(String id)
//    @Query(value = "{$project: {likeCount: {$size: '$likes'}}}", fields = "{ 'id': 1, 'authorId': 1, 'title': 1, 'likeCount': 1, 'createdAt': 1, 'updatedAt': 1 }")
//    List<Post> findAllWithoutComments();
//    @Query(fields = "{ 'comments': 1 }")
//    List<Comment> getComments(String postId);
}
