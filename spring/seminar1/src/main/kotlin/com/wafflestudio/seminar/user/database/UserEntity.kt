package com.wafflestudio.seminar.user.database

import com.wafflestudio.seminar.common.BaseTimeEntity
import com.wafflestudio.seminar.lecture.database.LectureEntity
import com.wafflestudio.seminar.user.domain.User
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity // ORM에서 테이블 가져와서 객체에 mapping 해줘! (spring-jpa 의존성 필요)
@Table(name = "users") // 안해두면 user_entity 이름으로 찾음. 우리의 테이블 이름은 users임
class UserEntity(
    // @Column 칼럼에 추가적 정보 알려줄 수 있음
    val name: String,
    var age: Int,
    // enum은 ordinal하게 읽을 수도, 이름을 읽을 수도 있음. 여기선 이름을 읽음
    // 안하면 숫자로 저장
    @Enumerated(EnumType.STRING)
    val gender: User.Gender,
    // 한 교수가 여러 강의를 맡음
    // @OneToMany(mappedBy = "instructor_id")
    // @OneToMany(mappedBy = "instructor") // lectureEntity가 가지고 있음
    // 오브젝트는 아는데 DB는 user와 lecture의 관계를 모름!
    // lecture에 user에 대한 정보는 뭐야? mappedBy
    // val lectures: List<LectureEntity>
) : BaseTimeEntity() {

    fun toUser(): User {
        return User(
            name = name,
            age = age,
            gender = gender,
        )
    }
}