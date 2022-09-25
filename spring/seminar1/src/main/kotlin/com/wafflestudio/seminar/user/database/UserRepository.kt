package com.wafflestudio.seminar.user.database

import org.springframework.data.jpa.repository.JpaRepository

// object와 RDB table mapping
// @Component 없어도 알아서 JpaRepository가 알아들음
interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByName(name: String): UserEntity
    // fun findByNameAndAgeBetweenAndGender(name: String): UserEntity 매번 선언 매우 번거로움
    // QueryDSL 등의 프레임워크
}