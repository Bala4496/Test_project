package com.example.test.services

import com.example.test.model.Like
import com.example.test.model.Post

interface PostService {
    List<Post> getAllPosts()
    List<Post> getAllPostsByUserId(String userId)
    Post getPostById(String id)
    boolean existPostById(String id)
    Post createPost(Post post)
    Post updatePost(String id, Post post)
    void deletePost(String id)
    void likePost(Like like)
    void unlikePost(Like like)
}