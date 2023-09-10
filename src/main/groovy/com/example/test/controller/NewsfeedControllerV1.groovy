package com.example.test.controller

import com.example.test.dto.NewsfeedPostDTO
import com.example.test.mapper.NewsfeedPostMapper
import com.example.test.services.NewsfeedService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/newsfeed")
class NewsfeedControllerV1 {

    NewsfeedService newsfeedService
    NewsfeedPostMapper newsfeedPostMapper

    NewsfeedControllerV1(NewsfeedService newsfeedService,
                         NewsfeedPostMapper newsfeedPostMapper) {
        this.newsfeedService = newsfeedService
        this.newsfeedPostMapper = newsfeedPostMapper
    }

    @GetMapping
    List<NewsfeedPostDTO> getNewsfeed() {
        newsfeedService.assembleNewsfeed()
                .collect(post -> newsfeedPostMapper.map(post))
    }

    @GetMapping("/user/{id}")
    List<NewsfeedPostDTO> getNewsfeedByUserId(@PathVariable String id) {
        newsfeedService.assembleNewsfeedForUserId(id)
                .collect(post -> newsfeedPostMapper.map(post))
    }

}
