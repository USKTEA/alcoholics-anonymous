package project.structure.admin.sample

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class SampleRouter {

    @Bean
    fun adminMemberRoute(handler: SampleHandler) = coRouter {
        GET("/", handler::test)
    }
}
