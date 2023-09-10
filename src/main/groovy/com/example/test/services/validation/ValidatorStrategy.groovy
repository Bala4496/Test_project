package com.example.test.services.validation

import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class ValidatorStrategy {

    List<Validator> validators

    ValidatorStrategy(@Lazy List<Validator> validators) {
        this.validators = validators
    }

    void validate(Object entity) {
        validators.find {validator -> validator.support(entity) }
                .collect {validator -> validator.validate(entity) }
    }

}
