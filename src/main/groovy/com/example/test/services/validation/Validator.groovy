package com.example.test.services.validation

interface Validator <T> {

    void validate(T entity)

    boolean support(Object entity)
}
