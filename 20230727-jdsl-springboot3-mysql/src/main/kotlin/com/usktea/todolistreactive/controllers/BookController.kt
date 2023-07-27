package com.usktea.todolistreactive.controllers

import com.usktea.todolistreactive.applications.ReactiveBookService
import com.usktea.todolistreactive.entity.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/books")
class ReactiveBookController(
    private val bookService: ReactiveBookService,
) {

    @GetMapping("/backdoor")
    suspend fun backdoor(): ResponseEntity<Long> {
        val spec = CreateBookSpec(
            name = "fakebook",
            meta = BookMeta(
                isbn10 = "0358653037",
                isbn13 = "978-0358653035",
                subTitle = "Illustrated Edition",
                seriesInformation = "Load of The Rings",
                author = "J.R.R. Tolkien",
                contributors = "",
                publisher = "William Morrow; Illustrated edition (November 16, 2021)",
                keywords = "Load of The Rings,Illustrated Edition"
            )
        )

        val book = bookService.create(spec)

        return ResponseEntity.ok(book.id)
    }

    @PostMapping
    suspend fun createBook(@RequestBody spec: CreateBookSpec): ResponseEntity<Long> {
        val book = bookService.create(spec)

        return ResponseEntity.ok(book.id)
    }

    @GetMapping("/{bookId}")
    suspend fun findById(@PathVariable bookId: Long): ResponseEntity<Book> {
        val book = bookService.findById(bookId)

        return ResponseEntity.ok(book)
    }

    @GetMapping("/{bookId}/toVO")
    suspend fun findByIdToVO(@PathVariable bookId: Long): ResponseEntity<NameIsbnVO> {
        val book = bookService.findByIdToVO(bookId)

        return ResponseEntity.ok(book)
    }

    @GetMapping
    suspend fun findAll(@RequestParam("name") name: String): ResponseEntity<List<Book>> {
        val books = bookService.findAll(FindBookSpec(name = name))

        return ResponseEntity.ok(books)
    }

    @GetMapping("/paging")
    suspend fun findAllByPaging(
        pageable: Pageable,
        @RequestParam("name") name: String,
        @RequestParam("publisher") publisher: String? = null
    ): ResponseEntity<Page<Book>> {
        val books = bookService.findAll(FindBookSpec(name = name, publisher = publisher), pageable)

        return ResponseEntity.ok(books)
    }

    @PutMapping
    suspend fun update(@RequestBody spec: UpdateBookSpec): ResponseEntity<Int> {
        val updatedRow = bookService.update(spec)

        return ResponseEntity.ok(updatedRow)
    }

    @DeleteMapping
    suspend fun delete(@RequestBody spec: FindBookSpec): ResponseEntity<Int> {
        val updatedRow = bookService.delete(spec)

        return ResponseEntity.ok(updatedRow)
    }
}