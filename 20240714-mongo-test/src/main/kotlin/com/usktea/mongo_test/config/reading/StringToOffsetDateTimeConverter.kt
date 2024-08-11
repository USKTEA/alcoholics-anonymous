package com.htbeyond.library.mongodb.converter.reading

import com.htbeyond.library.mongodb.converter.CustomMongoConverter
import org.springframework.data.convert.ReadingConverter
import java.time.OffsetDateTime

@ReadingConverter
object StringToOffsetDateTimeConverter : CustomMongoConverter<String, OffsetDateTime> {

    override fun convert(source: String): OffsetDateTime {
        return OffsetDateTime.parse(source)
    }
}
