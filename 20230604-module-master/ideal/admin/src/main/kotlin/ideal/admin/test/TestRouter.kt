package ideal.admin.test

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class TestRouter {

    @Bean
    fun adminMemberRoute(handler: TestHandler) = coRouter {
        GET("/", handler::test)
//                GET("", handler::findAll)
//                GET("/{id}", handler::findById)
//                POST("/{id}", handler::update)
    }
}
