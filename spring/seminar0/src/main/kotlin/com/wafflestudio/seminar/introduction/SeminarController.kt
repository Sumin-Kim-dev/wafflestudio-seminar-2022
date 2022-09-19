package com.wafflestudio.seminar.introduction

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController // 어노테이션. REST API를 수신해서 받아들이겠다는 컴포넌트에 대한 선언
class SeminarController {
    @GetMapping("/user/me") // get 할거다는 표시
    fun userMe(
        @RequestHeader("MY-NAME") name: String, // 헤더를 받아서
    ): String {
        return "안녕하세요 $name 님 :)" // 문자열 리턴
    }
}