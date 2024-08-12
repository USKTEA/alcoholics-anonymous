package com.usktea.mongo_test.config.reading

import com.usktea.mongo_test.config.CustomMongoConverter
import org.springframework.data.convert.ReadingConverter
import java.time.OffsetTime

@ReadingConverter
object StringToOffsetTimeConverter : CustomMongoConverter<String, OffsetTime> {

    override fun convert(source: String): OffsetTime {
        return OffsetTime.parse(source)
    }
}
