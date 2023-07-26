package com.example.hibernatereactive.models

import jakarta.persistence.*
import jakarta.validation.constraints.Past
import jakarta.validation.constraints.Size
import java.time.LocalDate

@Entity
class Book(
    @Id
    @GeneratedValue
    private val id: Long? = null,

    @Size(min = 13, max = 13)
    private val isbn: String,

    @Size(max = 100)
    private val title: String,

    @Basic(fetch = FetchType.LAZY)
    @Past
    private val published: LocalDate,

    @Basic(fetch = FetchType.LAZY)
    private val coverImage: ByteArray = "Cover image for '${title}'".toByteArray(),

    @ManyToOne(fetch = FetchType.LAZY)
    private val author: Author
) {
    fun id(): Long {
        return this.id!!
    }

    fun title(): String {
        return this.title
    }
}