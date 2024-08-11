package com.htbeyond.library.mongodb.converter.writing

import com.htbeyond.library.mongodb.converter.CustomMongoConverter
import org.springframework.data.convert.WritingConverter
import java.time.OffsetTime

@WritingConverter
object OffsetTimeToStringConverter : CustomMongoConverter<OffsetTime, String> {

    override fun convert(source: OffsetTime): String {
        return source.toString()
    }
}
