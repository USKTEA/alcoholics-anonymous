package com.usktea.mongo_test.config

import org.springframework.core.convert.converter.Converter

interface CustomMongoConverter<S, T> : Converter<S, T>
