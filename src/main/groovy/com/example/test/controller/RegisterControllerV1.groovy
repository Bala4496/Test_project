package com.example.test.controller

import com.example.test.dto.RegisterDTO
import com.example.test.mapper.RegisterMapper
import com.example.test.model.User
import com.example.test.services.RegisterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/register")
class RegisterControllerV1 {

    RegisterService registerService
    RegisterMapper registerMapper

    @Autowired
    RegisterController(RegisterService registerService,
                       RegisterMapper registerMapper) {
        this.registerService = registerService
        this.registerMapper = registerMapper
    }

    @PostMapping
    RegisterDTO registerUser(@RequestBody User user) {
        registerMapper.map(registerService.registerUser(user))
    }
}
