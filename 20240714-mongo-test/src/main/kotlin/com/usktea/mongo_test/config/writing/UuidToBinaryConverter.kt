package com.usktea.mongo_test.config.writing

import com.usktea.mongo_test.config.CustomMongoConverter
import java.nio.ByteBuffer
import java.util.UUID
import org.bson.types.Binary
import org.springframework.data.convert.WritingConverter

@WritingConverter
class UuidToBinaryConverter : CustomMongoConverter<UUID, Binary> {
    override fun convert(source: UUID): Binary {
        val bb = ByteBuffer.wrap(ByteArray(16))
        bb.putLong(source.mostSignificantBits)
        bb.putLong(source.leastSignificantBits)
        return Binary(bb.array())
    }
}
