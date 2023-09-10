package com.example.test.services

import com.example.test.model.User

interface UserService {
    List<User> getAllUsers()
    User getUserById(String id)
    boolean existUserById(String id)
    User getUserByUsername(String username)
    boolean existUserByUsername(String username)
    User getUserByEmail(String email)
    boolean existUserByEmail(String email)
    User updateUser(String id, User updatedUser)
    void deleteUser(String id)
//    List<User> getFollowers(String id)
//    List<User> getFollowings(String id)
}