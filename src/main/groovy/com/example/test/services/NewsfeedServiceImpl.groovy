package com.example.test.services


import com.example.test.model.Post
import org.springframework.stereotype.Service

@Service
class NewsfeedServiceImpl implements NewsfeedService {

    PostService postService
    CommentService commentService
    LikeService likeService
    SubscriptionService subscriptionService

    NewsfeedServiceImpl(PostService postService,
                        CommentService commentService,
                        LikeService likeService,
                        SubscriptionService subscriptionService) {
        this.postService = postService
        this.commentService = commentService
        this.likeService = likeService
        this.subscriptionService = subscriptionService
    }

    @Override
    List<Post> assembleNewsfeed() {
        postService.getAllPosts()
                .collect { post -> collectPost(post) }
    }

    @Override
    List<Post> assembleNewsfeedForUserId(String userId) {
        def followingPosts = subscriptionService.getFollowings(userId)
                .collectMany { following -> postService.getAllPostsByUserId(following.getId()) }
        def userPosts = postService.getAllPostsByUserId(userId)

        (followingPosts + userPosts)
                .sort { a, b -> b.createdAt <=> a.createdAt }
                .collect { post -> collectPost(post) }
    }

    private Post collectPost(Post post) {
        post.setComments(commentService.getCommentsByPostId(post.getId()))
        post.setLikes(likeService.getLikesByPostId(post.getId()))
        return post
    }
}
