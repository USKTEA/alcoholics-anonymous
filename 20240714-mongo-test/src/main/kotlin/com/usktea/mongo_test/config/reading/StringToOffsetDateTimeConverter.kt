package com.usktea.mongo_test.config.reading

import com.usktea.mongo_test.config.CustomMongoConverter
import org.springframework.data.convert.ReadingConverter
import java.time.OffsetDateTime

@ReadingConverter
object StringToOffsetDateTimeConverter : CustomMongoConverter<String, OffsetDateTime> {

    override fun convert(source: String): OffsetDateTime {
        return OffsetDateTime.parse(source)
    }
}
