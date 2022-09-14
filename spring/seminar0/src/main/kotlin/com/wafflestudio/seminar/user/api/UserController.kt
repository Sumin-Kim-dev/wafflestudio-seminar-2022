package com.wafflestudio.seminar.user.api

import com.wafflestudio.seminar.user.domain.User
import com.wafflestudio.seminar.user.domain.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService, // 의존성 주입(생성자 주입 방식)
) {
    // 또 다른 주입 방식
    // @Autowired
    // private lateinit var userService: UserService
    
    @GetMapping("/user")
    fun getUser(
        @RequestParam userId: Int,
    ): User {
        return userService.getUser(userId)
    }
    
    // 위와 같은 행동을 함
    @GetMapping("/user/{userId}")
    fun getUser2(
        @PathVariable userId: Int,
    ): User {
        return userService.getUser(userId)
    }
}