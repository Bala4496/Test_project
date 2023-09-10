package com.example.test.services

import com.example.test.model.Comment
import com.example.test.repository.CommentRepository
import com.example.test.services.validation.ValidatorStrategy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

import java.time.LocalDateTime

@Service
class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository
    private ValidatorStrategy validatorStrategy

    @Autowired
    PostServiceImpl(CommentRepository commentRepository,
                    ValidatorStrategy validatorStrategy) {
        this.commentRepository = commentRepository
        this.validatorStrategy = validatorStrategy
    }

    @Override
    Comment addComment(String postId, Comment comment) {
        validatorStrategy.validate(comment)
        comment.setPostId(postId)
        def now = LocalDateTime.now()
        comment.setCreatedAt(now)
        comment.setUpdatedAt(now)
        commentRepository.save(comment)
    }

    @Override
    List<Comment> getCommentsByPostId(String postId) {
        commentRepository.getCommentsByPostId(postId)
    }

    @Override
    Comment updateComment(String commentId, Comment updatedComment) {
        commentRepository.findById(commentId).map(comment -> {
            println "comment"
            replaceUpdatedParams(updatedComment, comment)
            println "comment replaced"
            comment.setUpdatedAt(LocalDateTime.now())
            println "comment updated"
            commentRepository.save(comment)
        }).orElseThrow { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found with ID: ${commentId}") }
    }

    @Override
    void removeComment(String id) {
        commentRepository.deleteById(id)
    }

    void replaceUpdatedParams(Comment updatedComment, Comment comment) {
        if (Objects.nonNull(updatedComment.getMessage())) {
            comment.setMessage(updatedComment.getMessage())
        }
    }
}
