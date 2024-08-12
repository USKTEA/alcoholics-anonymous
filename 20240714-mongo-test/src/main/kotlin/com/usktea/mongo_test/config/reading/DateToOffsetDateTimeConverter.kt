package com.usktea.mongo_test.config.reading

import com.usktea.mongo_test.config.CustomMongoConverter
import org.springframework.data.convert.ReadingConverter
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.Date

@ReadingConverter
object DateToOffsetDateTimeConverter : CustomMongoConverter<Date, OffsetDateTime> {

    private val ZONE_OFFSET_ASIA_SEOUL = ZoneOffset.ofHours(9)

    override fun convert(source: Date): OffsetDateTime {
        return source.toInstant().atOffset(ZONE_OFFSET_ASIA_SEOUL)
    }
}
