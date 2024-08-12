package com.usktea.mongo_test.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.MongoTransactionManager
import org.springframework.data.mongodb.core.convert.MongoCustomConversions

@Configuration
class LibraryMongodbConfig {

    @Bean
    fun getMongoCustomConversions(customMongoConverterList: List<CustomMongoConverter<*, *>>): MongoCustomConversions {
        return MongoCustomConversions(customMongoConverterList)
    }

    @ConditionalOnMissingBean
    @Bean
    fun getMongoTransactionManager(mongoDatabaseFactory: MongoDatabaseFactory): MongoTransactionManager {
        return MongoTransactionManager(mongoDatabaseFactory)
    }
}
