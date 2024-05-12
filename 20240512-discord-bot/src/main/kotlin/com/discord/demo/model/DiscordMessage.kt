package com.discord.demo.model

data class DiscordMessage(
    val content: String
) {
    companion object {
        fun from(message: Message): DiscordMessage {
            return DiscordMessage(
                content = message.content()
            )
        }
    }
}
