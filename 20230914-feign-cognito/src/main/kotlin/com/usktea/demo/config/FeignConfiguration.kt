package com.usktea.demo.config

import feign.Logger
import feign.codec.Encoder
import feign.form.spring.SpringFormEncoder
import org.springframework.beans.factory.ObjectFactory
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.support.SpringEncoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
@EnableFeignClients("com.usktea.demo.feign")
class FeignConfiguration {

    @Bean
    fun feignLoggerLevel(): Logger.Level {
        return Logger.Level.FULL
    }

//    @Bean
//    fun encoder(converters: ObjectFactory<HttpMessageConverters>): Encoder {
//        return SpringFormEncoder(SpringEncoder(converters))
//    }
}
