package com.example.test.services

import com.example.test.model.User
import com.example.test.repository.UserRepository
import com.example.test.services.validation.ValidatorStrategy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.time.LocalDateTime

@Service
class RegisterServiceImpl implements RegisterService {

    UserRepository userRepository
    ValidatorStrategy validatorStrategy

    @Autowired
    RegisterServiceImpl(UserRepository userRepository,
                        ValidatorStrategy validatorStrategy) {
        this.userRepository = userRepository
        this.validatorStrategy = validatorStrategy
    }

    @Override
    User registerUser(User user) {
        validatorStrategy.validate(user)
        def now = LocalDateTime.now()
        user.setCreatedAt(now)
        user.setUpdatedAt(now)
        userRepository.save(user)
    }
}
