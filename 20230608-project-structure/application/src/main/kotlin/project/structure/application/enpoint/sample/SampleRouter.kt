package project.structure.application.enpoint.sample

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class SampleRouter {

    @Bean
    fun sampleRoute(handler: SampleHandler) = coRouter {
        GET("/sample", handler::test)
        POST("/sample", handler::create)
    }
}
