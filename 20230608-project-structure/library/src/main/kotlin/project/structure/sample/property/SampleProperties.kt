package project.structure.sample.property

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnProperty(name = ["mode"], havingValue = "application")
class SampleProperties {
    @Value("\${appkey}")
    lateinit var key : String
}