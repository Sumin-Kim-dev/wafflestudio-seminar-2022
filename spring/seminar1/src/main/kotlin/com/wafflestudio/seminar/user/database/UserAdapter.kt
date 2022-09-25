package com.wafflestudio.seminar.user.database

import com.wafflestudio.seminar.user.domain.User
import com.wafflestudio.seminar.user.domain.UserPort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UserAdapter( // Port & Adapter 패턴. '클린 아키텍처' 참고
    // domain-db 참조관계 정의에 사용
    private val userRepository: UserRepository,
) : UserPort {
    override fun getUser(id: Int): User {
        // 새 데이터 넣기
        // val newUser = UserEntity("sdf", 213, User.Gender.MALE)
        // userRepository.save(newUser)
        val entity = userRepository.findByIdOrNull(id.toLong()) ?: throw IllegalArgumentException("USER#$id NOT FOUND!")
        return entity.toUser()
    }
    
    @Transactional // 변경 감지해서 반영!
    fun createUser(name: String) {
        val newUser = UserEntity("sdf", 123, User.Gender.MALE)
        userRepository.save(newUser)
    }
    
    fun updateUser(name: String) {
        val newUser = userRepository.findByName(name) // 인터페이스에 메소드 선언만 해줘도 ok
        newUser.age = 121
        userRepository.save(newUser) // 이렇게도 가능하지만 귀찮으므로 어노테이션 이용 추천
    }
}