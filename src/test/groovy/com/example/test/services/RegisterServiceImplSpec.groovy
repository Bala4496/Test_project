//package com.example.test.services
//
//import com.example.test.model.User
//import com.example.test.repository.UserRepository
//import com.example.test.services.validation.ValidatorStrategy
//import org.mockito.Mock
//import org.mockito.MockitoAnnotations
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.SpringApplication
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.http.HttpStatus
//import org.springframework.test.context.ContextConfiguration
//import org.springframework.web.server.ResponseStatusException
//import spock.lang.Specification
//
//@SpringBootTest
//class RegisterServiceImplSpec extends Specification {
//
//    @Autowired
//    ValidatorStrategy validatorStrategy
//
//    def "Register a user successfully"() {
//        given:
//        def user = new User(username: "John Doe", password: "password", email: "email@gmail.com")
//
//        UserRepository userRepository = GroovyMock(UserRepository)
//        userRepository.save(user) >> { User u ->
//            u.setCreatedAt(u.getCreatedAt())
//            u.setUpdatedAt(u.getUpdatedAt())
//            u
//        }
//
//        ValidatorStrategy validatorStrategy = GroovyMock(ValidatorStrategy)
//        validatorStrategy.validate(user) >> { }
//        def registerService = new RegisterServiceImpl(userRepository, validatorStrategy)
//
//        when:
//        def registeredUser = registerService.registerUser(user)
//
//        then:
//        1 * userRepository.save(user)
//        registeredUser.getCreatedAt() != null
//        registeredUser.getUpdatedAt() != null
//        registeredUser.getCreatedAt() == registeredUser.getUpdatedAt()
//    }
//
//    def "Test registration with invalid username"() {
//        given:
//        def user = new User(username: null, password: "password", email: "email@gmail.com")
//
//        userRepository.save(user) >> { User u -> u }
//        validatorStrategy.validate(_) >> {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is empty")
//        }
//
//        when:
//        def registeredUser = registerService.registerUser(user)
//
//        then:
//        1 * userRepository.save(_)
//        registeredUser == null
//    }
//}
//
