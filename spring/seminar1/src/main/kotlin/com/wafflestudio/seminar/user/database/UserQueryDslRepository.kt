package com.wafflestudio.seminar.user.database

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.wafflestudio.seminar.lecture.database.QLectureEntity.lectureEntity
import com.wafflestudio.seminar.user.database.QUserEntity.userEntity
import com.wafflestudio.seminar.user.domain.User
import org.springframework.stereotype.Component

@Component
class UserQueryDslRepository(
    private val queryFactory: JPAQueryFactory,
) {
    fun example(): List<UserEntity> =
        queryFactory
            .select(userEntity)
            //.select(
            //    Projections.constructor(
            //        User::class.java,
            //    )
            //) Entity가 아닌 평타입으로 가져오는 경우
            .from(userEntity)
            .innerJoin(lectureEntity)
            //.on(lectureEntity.eq())
            .where(
                userEntity.name.eq(""),
                userEntity.age.eq(123),
                userEntity.gender.eq(User.Gender.MALE)
            )
            .fetch()
            //.fetchOne() : return UserEntity
            //?: throw IllegalArgumentException("없어요!") 
}