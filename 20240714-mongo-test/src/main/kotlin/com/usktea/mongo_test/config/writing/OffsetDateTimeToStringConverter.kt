package com.htbeyond.library.mongodb.converter.writing

import com.htbeyond.library.mongodb.converter.CustomMongoConverter
import org.springframework.data.convert.WritingConverter
import java.time.OffsetDateTime
import java.time.ZoneOffset

@WritingConverter
object OffsetDateTimeToStringConverter : CustomMongoConverter<OffsetDateTime, String> {

    override fun convert(source: OffsetDateTime): String {
        return source.withOffsetSameInstant(ZoneOffset.ofHours(9)).toString()
    }
}
