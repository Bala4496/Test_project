package com.example.test.model

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "likes")
class Like {
    String id
    String postId
    String userId

    boolean equals(o) {
        if (this.is(o)) return true
        if (o == null || getClass() != o.class) return false
        return userId == ((Like) o).userId
    }

    int hashCode() {
        return (userId != null ? userId.hashCode() : 0)
    }
}
