import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class AdminMemberRouter {
    val basePath = "/admin/v1/adminmember"

    @Bean
    fun adminMemberRoute(handler: AdminMemberHandler) = coRouter {
        path(basePath).nest {
            accept(MediaType.APPLICATION_JSON).nest {
//                GET("", handler::create)
//                GET("", handler::findAll)
//                GET("/{id}", handler::findById)
//                POST("/{id}", handler::update)
            }
        }
    }
}