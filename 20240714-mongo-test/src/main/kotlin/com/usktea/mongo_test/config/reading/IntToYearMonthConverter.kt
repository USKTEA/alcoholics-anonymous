package com.usktea.mongo_test.config.reading

import com.usktea.mongo_test.config.CustomMongoConverter
import org.springframework.data.convert.ReadingConverter
import java.time.YearMonth

@ReadingConverter
object IntToYearMonthConverter : CustomMongoConverter<Int, YearMonth> {

    override fun convert(source: Int): YearMonth {
        return YearMonth.of(source / 100, source % 100)
    }
}
