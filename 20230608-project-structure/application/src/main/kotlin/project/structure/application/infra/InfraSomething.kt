package project.structure.application.infra

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class InfraSomething {
    @Value("\${appkey}")
    lateinit var appKey : String

    fun key(): String {
        return appKey
    }
}