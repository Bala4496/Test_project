package com.example.test.controller

import com.example.test.dto.CommentDTO
import com.example.test.mapper.CommentMapper
import com.example.test.model.Comment
import com.example.test.services.CommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/comments")
class CommentControllerV1 {

    CommentService commentService
    CommentMapper commentMapper

    @Autowired
    CommentController(CommentService commentService,
                      CommentMapper commentMapper) {
        this.commentService = commentService
        this.commentMapper = commentMapper
    }

    @GetMapping("/post/{id}")
    List<CommentDTO> getCommentsByPostId(@PathVariable String id) {
        commentService.getCommentsByPostId(id)
                .collect { comment -> commentMapper.map(comment) }
    }

    @PostMapping("/post/{id}")
    CommentDTO addComment(@PathVariable String id, @RequestBody Comment comment) {
        commentMapper.map(commentService.addComment(id, comment))
    }

    @PutMapping("/{id}")
    CommentDTO updateComment(@PathVariable String id, @RequestBody Comment comment) {
        commentMapper.map(commentService.updateComment(id, comment))
    }

    @DeleteMapping("/{id}")
    void deleteComment(@PathVariable String id) {
        commentService.removeComment(id)
    }
}
