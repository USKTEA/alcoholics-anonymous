package com.discord.demo.builder

import org.springframework.stereotype.Component
import java.time.DayOfWeek

@Component
class MessageBuilderFactory(
    val messageBuilders: List<MessageBuilder>
) {
    operator fun get(dayOfWeek: DayOfWeek): MessageBuilder {
        return messageBuilders.first { it.support(dayOfWeek) }
    }
}
