package com.wafflestudio.seminar.core.user.api

import com.wafflestudio.seminar.common.Authenticated
import com.wafflestudio.seminar.common.UserContext
import com.wafflestudio.seminar.core.user.domain.User
import com.wafflestudio.seminar.core.user.domain.UserService
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.event.EventListener
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException.Forbidden
import javax.annotation.PostConstruct

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
) {
    @Authenticated
    @GetMapping
    fun getUser(
//        인증을 일일히 API마다 하기엔 너무 많다! interceptor로 처리할 수 있음
//        @RequestHeader("Authorization") authToken: String,
        @RequestParam userId: Int,
    ): User {
//        val result = authService.doAuth(authToken)
//        
//        if (!result) {
//            throw Forbidden("로그인 실패")
//        }
        return userService.getUser(userId)
    }

    @Authenticated
    @GetMapping("/me")
    fun getUserMe(
//        @UserContext userId: Long,
    ): User {
        return userService.getUser(0)
//        return userService.getUser(userId.toInt())
    }

    /**
     * 트랜잭션의 작동을 확인하기 위해, 부팅 시에 10명의 랜덤 유저를 만들어볼게요.
     * 트랜잭션은 AOP 방식으로 동작해요. 따라서 다른 컴포넌트에서 함수를 호출할 때에만 동작해요.
     */
    @EventListener(ApplicationStartedEvent::class)
    fun createRandomUser() {
        (1..10).forEach { _ ->
            try {
                userService.createRandomUser()
            } catch (e: Exception) {
                // Do Nothing On Exception
            }
        }
        
        println("살아남은 유저들: ${userService.getAllUsers()}")
    }
}