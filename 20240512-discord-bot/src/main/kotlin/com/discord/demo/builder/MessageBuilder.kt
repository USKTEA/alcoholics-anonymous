package com.discord.demo.builder

import com.discord.demo.model.*
import org.springframework.stereotype.Component
import java.time.DayOfWeek

interface MessageBuilder {
    fun support(dayOfWeek: DayOfWeek): Boolean
    fun build(): Message
}

@Component
class MondayMessageBuilder : MessageBuilder {
    override fun support(dayOfWeek: DayOfWeek): Boolean {
        return dayOfWeek == DayOfWeek.MONDAY
    }

    override fun build(): Message {
        return MondayMessage()
    }
}

@Component
class TuesdayMessageBuilder : MessageBuilder {
    override fun support(dayOfWeek: DayOfWeek): Boolean {
        return dayOfWeek == DayOfWeek.TUESDAY
    }

    override fun build(): Message {
        return TuesdayMessage()
    }
}

@Component
class WednesdayMessageBuilder : MessageBuilder {
    override fun support(dayOfWeek: DayOfWeek): Boolean {
        return dayOfWeek == DayOfWeek.WEDNESDAY
    }

    override fun build(): Message {
        return WednesdayMessage()
    }
}

@Component
class ThursdayMessageBuilder : MessageBuilder {
    override fun support(dayOfWeek: DayOfWeek): Boolean {
        return dayOfWeek == DayOfWeek.THURSDAY
    }

    override fun build(): Message {
        return ThursdayMessage()
    }
}

@Component
class FridayMessageBuilder : MessageBuilder {
    override fun support(dayOfWeek: DayOfWeek): Boolean {
        return dayOfWeek == DayOfWeek.FRIDAY
    }

    override fun build(): Message {
        return FridayMessage()
    }
}
