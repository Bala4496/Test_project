package com.example.test.services

import com.example.test.model.Post

interface NewsfeedService {
    List<Post> assembleNewsfeed()
    List<Post> assembleNewsfeedForUserId(String userId)
}