package com.htbeyond.library.mongodb.converter.reading

import com.htbeyond.library.mongodb.converter.CustomMongoConverter
import org.springframework.data.convert.ReadingConverter
import java.time.OffsetTime

@ReadingConverter
object StringToOffsetTimeConverter : CustomMongoConverter<String, OffsetTime> {

    override fun convert(source: String): OffsetTime {
        return OffsetTime.parse(source)
    }
}
