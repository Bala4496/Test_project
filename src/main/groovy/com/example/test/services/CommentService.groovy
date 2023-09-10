package com.example.test.services

import com.example.test.model.Comment

interface CommentService {
    Comment addComment(String postId, Comment comment)
    List<Comment> getCommentsByPostId(String postId)
    Comment updateComment(String postId, Comment comment)
    void removeComment(String id)
}