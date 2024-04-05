package com.usktea.elasitc.document

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

@Document(indexName = "books")
class Book(
    @Id
    val id: String,
    @Field(type = FieldType.Text)
    val name: String,
    @Field(type = FieldType.Text)
    val summary: String,
    @Field(type = FieldType.Integer)
    val price: Int,
)
