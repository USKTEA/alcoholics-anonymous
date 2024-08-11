package com.htbeyond.library.mongodb.converter.writing

import com.htbeyond.library.mongodb.converter.CustomMongoConverter
import org.springframework.data.convert.WritingConverter
import java.time.OffsetDateTime
import java.util.Date

@WritingConverter
object OffsetDateTimeToDateConverter : CustomMongoConverter<OffsetDateTime, Date> {

    override fun convert(source: OffsetDateTime): Date {
        return Date.from(source.toInstant())
    }
}
