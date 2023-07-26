package com.example.hibernatereactive.models

import jakarta.persistence.*
import jakarta.persistence.CascadeType.PERSIST
import jakarta.validation.constraints.Size


@Entity
class Author(
    @Id
    @GeneratedValue
    private val id: Long? = null,

    @Size(max = 100)
    private val name: String,

    @OneToMany(mappedBy = "author", cascade = [PERSIST])
    private val books: MutableList<Book> = mutableListOf(),
) {
    fun addBook(book: Book) {
        this.books.add(book)
    }
}