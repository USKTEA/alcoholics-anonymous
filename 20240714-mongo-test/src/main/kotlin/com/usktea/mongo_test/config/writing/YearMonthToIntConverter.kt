package com.htbeyond.library.mongodb.converter.writing

import com.htbeyond.library.mongodb.converter.CustomMongoConverter
import org.springframework.data.convert.WritingConverter
import java.time.YearMonth

@WritingConverter
object YearMonthToIntConverter : CustomMongoConverter<YearMonth, Int> {

    override fun convert(source: YearMonth): Int {
        return source.year * 100 + source.month.value
    }
}
