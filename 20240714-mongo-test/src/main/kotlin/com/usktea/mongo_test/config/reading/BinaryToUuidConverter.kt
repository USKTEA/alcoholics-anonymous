package com.usktea.mongo_test.config.reading

import com.usktea.mongo_test.config.CustomMongoConverter
import java.nio.ByteBuffer
import java.util.UUID
import org.bson.types.Binary
import org.springframework.data.convert.ReadingConverter

@ReadingConverter
class BinaryToUuidConverter : CustomMongoConverter<Binary, UUID> {
    override fun convert(source: Binary): UUID {
        val bytes = source.data
        val bb = ByteBuffer.wrap(bytes)
        val high = bb.long
        val low = bb.long
        return UUID(high, low)
    }
}
