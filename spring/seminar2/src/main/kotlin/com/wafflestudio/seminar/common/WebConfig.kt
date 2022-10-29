package com.wafflestudio.seminar.common

import com.wafflestudio.seminar.core.user.domain.UserService
import org.springframework.context.annotation.Configuration
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.client.HttpClientErrorException.Forbidden
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.HandlerMethod
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class WebConfig(
    private val authInterceptor: AuthInterceptor,
    private val authArgumentResolver: AuthArgumentResolver,
): WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authInterceptor)
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(authArgumentResolver)
    }
}

@Configuration
class AuthArgumentResolver(
//    private val userService: UserService,
): HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(UserContext::class.java) && parameter.parameterType == Long::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        parameter.hasMethodAnnotation(UserContext::class.java)
//        뜯어온 헤더로 적절한 유저아이디 추출해서 리턴
//        val user = userService.getUser(???)
//        return ???
        val password = (webRequest as ServletWebRequest).request.getAttribute("pwd")
        return password
    }
}

@Configuration
class AuthInterceptor(
//    private val service: UserService,
): HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val handlerCasted = (handler as? HandlerMethod) ?: return true
        
        val needAuthenticated = handlerCasted.hasMethodAnnotation(Authenticated::class.java)
        
        if (needAuthenticated) {
            // 인증은? 어노테이션 & ArgumentResolver
            val password = request.getHeader("AUTH")
            request.setAttribute("pwd", password)
            // 주입 받아서 인증을 할 수 있다!
//            val result = authService.doAuth(authHeader)
//            if (!result) {
//                throw Forbidden()
//            }
        }
        return super.preHandle(request, response, handler)
    }
}