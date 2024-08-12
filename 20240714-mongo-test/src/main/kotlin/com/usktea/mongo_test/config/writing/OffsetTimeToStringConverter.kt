package com.usktea.mongo_test.config.writing

import com.usktea.mongo_test.config.CustomMongoConverter
import org.springframework.data.convert.WritingConverter
import java.time.OffsetTime

@WritingConverter
object OffsetTimeToStringConverter : CustomMongoConverter<OffsetTime, String> {

    override fun convert(source: OffsetTime): String {
        return source.toString()
    }
}
