package project.structure.application.enpoint.sample

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class SampleRouter {

    @Bean
<<<<<<< HEAD:20230608-project-structure/application/src/main/kotlin/project/structure/application/sample/SampleRouter.kt
    fun adminMemberRoute(handler: SampleHandler) = coRouter {
        GET("/", handler::test)
=======
    fun sampleRoute(handler: SampleHandler) = coRouter {
        GET("/sample", handler::test)
>>>>>>> 3fdbef6b7760b5db39cf4da1692a289cddd233fc:20230608-project-structure/application/src/main/kotlin/project/structure/application/enpoint/sample/SampleRouter.kt
        POST("/sample", handler::create)
    }
}
