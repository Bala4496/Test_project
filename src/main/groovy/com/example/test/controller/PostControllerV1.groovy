package com.example.test.controller


import com.example.test.dto.PostDTO
import com.example.test.mapper.PostMapper
import com.example.test.model.Like
import com.example.test.model.Post
import com.example.test.services.PostService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/posts")
class PostControllerV1 {

    PostService postService
    PostMapper postMapper

    PostControllerV1(PostService postService,
                     PostMapper postMapper) {
        this.postService = postService
        this.postMapper = postMapper
    }

    @GetMapping
    List<PostDTO> getAllPosts() {
        postService.getAllPosts()
                .collect { post -> postMapper.map(post) }
    }

    @GetMapping("/{id}")
    PostDTO getPostById(@PathVariable String id) {
        postMapper.map(postService.getPostById(id))
    }

    @GetMapping("/user/{id}")
    List<PostDTO> getAllPostsByUserId(@PathVariable String id) {
        postService.getAllPostsByUserId(id)
                .collect { post -> postMapper.map(post)}
    }

    @PostMapping
    PostDTO createPost(@RequestBody Post post) {
        postMapper.map(postService.createPost(post))
    }

    @PutMapping("/{id}")
    PostDTO updatePost(@PathVariable String id, @RequestBody Post post) {
        postMapper.map(postService.updatePost(id, post))
    }

    @DeleteMapping("/{id}")
    void deletePost(@PathVariable String id) {
        postService.deletePost(id)
    }

    @PostMapping("/{id}/like")
    void like(@PathVariable String id, @RequestBody Like like) {
        like.setPostId(id)
        postService.likePost(like)
    }

    @PostMapping("/{id}/unlike")
    void unlike(@PathVariable String id, @RequestBody Like like) {
        like.setPostId(id)
        postService.unlikePost(like)
    }
}
