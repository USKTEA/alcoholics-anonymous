package com.usktea.mongo_test.config.writing

import com.usktea.mongo_test.config.CustomMongoConverter
import org.springframework.data.convert.WritingConverter
import java.time.ZonedDateTime
import java.util.Date

@WritingConverter
object ZonedDateTimeToDateConverter : CustomMongoConverter<ZonedDateTime, Date> {

    override fun convert(source: ZonedDateTime): Date {
        return Date.from(source.toInstant())
    }
}
