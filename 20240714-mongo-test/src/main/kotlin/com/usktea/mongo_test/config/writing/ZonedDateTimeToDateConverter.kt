package com.htbeyond.library.mongodb.converter.writing

import com.htbeyond.library.mongodb.converter.CustomMongoConverter
import org.springframework.data.convert.WritingConverter
import java.time.ZonedDateTime
import java.util.Date

@WritingConverter
object ZonedDateTimeToDateConverter : CustomMongoConverter<ZonedDateTime, Date> {

    override fun convert(source: ZonedDateTime): Date {
        return Date.from(source.toInstant())
    }
}
