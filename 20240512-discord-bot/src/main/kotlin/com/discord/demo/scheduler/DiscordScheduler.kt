package com.discord.demo.scheduler

import com.discord.demo.builder.MessageBuilderFactory
import com.discord.demo.logging.logger
import com.discord.demo.model.DiscordMessage
import org.springframework.http.MediaType
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId

@Component
class DiscordScheduler(
    private val restClient: RestClient,
    private val messageBuilderFactory: MessageBuilderFactory,
) {

    @Scheduled(cron = "0 30 19 * * *", zone = "Asia/Seoul")
    fun sendMessage() {
        val dayOfWeek = LocalDate.now(ZoneId.of("Asia/Seoul")).dayOfWeek

        if (isWeekend(dayOfWeek)) {
            return
        }

        val messageBuilder = messageBuilderFactory[dayOfWeek]
        val message = messageBuilder.build()

        try {
            restClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(DiscordMessage.from(message))
                .retrieve()
        } catch (exception: Exception) {
            logger.error("Send Message Error", exception)

            throw exception
        }

        logger.info("Send Message Success")
    }

    private fun isWeekend(dayOfWeek: DayOfWeek): Boolean {
        return dayOfWeek == DayOfWeek.SUNDAY || dayOfWeek == DayOfWeek.SATURDAY
    }
}
