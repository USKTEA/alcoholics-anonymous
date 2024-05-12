package com.discord.demo.logging

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.full.companionObject

val <T : Any> T.logger: Logger
    get() {
        return LoggerFactory.getLogger(
            javaClass.enclosingClass
                ?.takeIf {
                    it.kotlin.companionObject?.java == javaClass
                }
                ?: javaClass,
        )
    }
