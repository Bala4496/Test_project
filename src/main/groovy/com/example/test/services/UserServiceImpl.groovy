package com.example.test.services

import com.example.test.model.User
import com.example.test.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

import java.time.LocalDateTime

@Service
class UserServiceImpl implements UserService {

    private UserRepository userRepository

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository
    }

    @Override
    List<User> getAllUsers() {
        userRepository.findAll()
    }

    @Override
    User getUserById(String id) {
        userRepository.findById(id)
                .orElseThrow { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: ${id}") }
    }

    @Override
    boolean existUserById(String id) {
        userRepository.existsById(id)
    }

    @Override
    User getUserByUsername(String username) {
        userRepository.findByUsername(username)
                .orElseThrow { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with username: ${username}") }
    }

    @Override
    boolean existUserByUsername(String username) {
        userRepository.existsByUsername(username)
    }

    @Override
    User getUserByEmail(String email) {
        userRepository.findByEmail(email)
                .orElseThrow { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with email: ${email}") }
    }

    @Override
    boolean existUserByEmail(String email) {
        userRepository.existsByEmail(email)
    }

    @Override
    User updateUser(String id, User updatedUser) {
        userRepository.findById(id).map(user -> {
            replaceUpdatedParams(updatedUser, user)
            user.setUpdatedAt(LocalDateTime.now())
            userRepository.save(user)
        }).orElseThrow { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: ${id}") }
    }

    @Override
    void deleteUser(String id) {
        userRepository.deleteById(id)
    }

    private void replaceUpdatedParams(User updatedUser, User user) {
        if (Objects.nonNull(updatedUser.getUsername())) {
            user.setUsername(updatedUser.getUsername())
        }
        if (Objects.nonNull(updatedUser.getEmail())) {
            user.setEmail(updatedUser.getEmail())
        }
        if (Objects.nonNull(updatedUser.getPassword())) {
            user.setPassword(updatedUser.getPassword())
        }
    }
}
