package com.usktea.mongo_test.config

import com.usktea.mongo_test.config.reading.BinaryToUuidConverter
import com.usktea.mongo_test.config.reading.StringToOffsetDateTimeConverter
import com.usktea.mongo_test.config.writing.OffsetDateTimeToStringConverter
import com.usktea.mongo_test.config.writing.UuidToBinaryConverter
import java.time.OffsetDateTime
import java.util.Optional
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.auditing.DateTimeProvider
import org.springframework.data.mongodb.config.EnableMongoAuditing

@Configuration
@EnableMongoAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
class MongoConfig {

    @Bean(name = ["auditingDateTimeProvider"])
    fun getDateTimeProvider(): DateTimeProvider {
        return DateTimeProvider { Optional.of(OffsetDateTime.now()) }
    }

    @Bean
    fun getOffsetDateTimeToStringConverter(): OffsetDateTimeToStringConverter {
        return OffsetDateTimeToStringConverter
    }

    @Bean
    fun getStringToOffsetDateTimeConverter(): StringToOffsetDateTimeConverter {
        return StringToOffsetDateTimeConverter
    }

    @Bean
    fun uuidToBinaryConverter(): UuidToBinaryConverter {
        return UuidToBinaryConverter()
    }

    @Bean
    fun binaryToUuidConverter(): BinaryToUuidConverter {
        return BinaryToUuidConverter()
    }
}
