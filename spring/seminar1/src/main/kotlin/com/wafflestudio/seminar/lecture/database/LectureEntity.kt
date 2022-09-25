package com.wafflestudio.seminar.lecture.database

import com.wafflestudio.seminar.common.BaseTimeEntity
import com.wafflestudio.seminar.user.database.UserEntity
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
class LectureEntity(
    val title: String,
    // 여러 강의가 한 교수한테 향할 수 있다
    @ManyToOne(fetch = FetchType.LAZY) // N + 1 문제와 관련됨
    // DB의 입장에서는 lecture -> user 테이블 조회 두번
    // user 입장에서 lecture가 100개 있다면? 100개 더 조회함?
    // user 한명 조회하는데 조회가 101번. N + 1 문제!
    // 바로 갖고 오지말고 원할때 가져오게 해줘! LAZY
    val instructor: UserEntity,
    // 실제 DB
    // val instructor_id: Long
) : BaseTimeEntity()