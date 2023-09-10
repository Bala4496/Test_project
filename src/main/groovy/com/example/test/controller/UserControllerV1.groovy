package com.example.test.controller

import com.example.test.dto.UserDTO
import com.example.test.mapper.UserMapper
import com.example.test.model.User
import com.example.test.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users")
class UserControllerV1 {

    UserService userService
    UserMapper userMapper

    @Autowired
    UserController(UserService userService,
                   UserMapper userMapper) {
        this.userService = userService
        this.userMapper = userMapper
    }

    @GetMapping
    List<UserDTO> getAllUsers() {
        userService.getAllUsers()
                .collect { user -> userMapper.map(user) }
    }

    @GetMapping("/{id}")
    UserDTO getUserById(@PathVariable String id) {
        userMapper.map(userService.getUserById(id))
    }

    @GetMapping("/username/{username}")
    UserDTO getUserByUsername(@PathVariable String username) {
        userMapper.map(userService.getUserByUsername(username))
    }

    @GetMapping("/email/{email}")
    UserDTO getUserByEmail(@PathVariable String email) {
        userMapper.map(userService.getUserByEmail(email))
    }

    @PutMapping("/{id}")
    UserDTO updateUser(@PathVariable String id, @RequestBody User user) {
        userMapper.map(userService.updateUser(id, user))
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable String id) {
        userService.deleteUser(id)
    }
}
