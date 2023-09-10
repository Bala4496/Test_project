package com.example.test.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

import java.time.LocalDateTime

@Document(collection = "users")
class User {
    @Id
    String id
    @Indexed
    String username
    String email
    String password
    LocalDateTime createdAt
    LocalDateTime updatedAt

    @Override
    String toString() {
        return "User: $id, $username, $email, $password, $createdAt, $updatedAt"
    }
}
