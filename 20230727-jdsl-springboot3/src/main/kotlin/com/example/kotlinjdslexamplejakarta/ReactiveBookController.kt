package com.example.kotlinjdslexamplejakarta

import com.example.kotlinjdslexamplejakarta.bookSpecs.CreateBookSpec
import com.example.kotlinjdslexamplejakarta.bookSpecs.FindBookSpec
import com.example.kotlinjdslexamplejakarta.bookSpecs.NameIsbnVO
import com.example.kotlinjdslexamplejakarta.bookSpecs.UpdateBookSpec
import com.example.kotlinjdslexamplejakarta.entity.Book
import com.example.kotlinjdslexamplejakarta.entity.BookMeta
import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.linecorp.kotlinjdsl.querydsl.from.associate
import com.linecorp.kotlinjdsl.querydsl.where.WhereDsl
import com.linecorp.kotlinjdsl.spring.data.reactive.query.*
import io.smallrye.mutiny.coroutines.awaitSuspending
import org.hibernate.reactive.mutiny.Mutiny.SessionFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/books/reactive")
class ReactiveBookController(
    private val bookService: ReactiveBookService,
) {
    @GetMapping("/backdoor")
    suspend fun fake(): ResponseEntity<Long> {
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
        println(pageable)
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

@Service
class ReactiveBookService(
    private val sessionFactory: SessionFactory,
    private val queryFactory: SpringDataHibernateMutinyReactiveQueryFactory,
) {
    suspend fun create(spec: CreateBookSpec): Book {
        return Book(name = spec.name, meta = spec.meta).also {
            sessionFactory.withSession { session -> session.persist(it).flatMap { session.flush() } }
                .awaitSuspending()
        }
    }

    suspend fun findById(id: Long): Book {
        return queryFactory.singleQuery {
            select(entity(Book::class))
            from(entity(Book::class))
            where(col(Book::id).equal(id))
        }
    }

    suspend fun findByIdToVO(id: Long): NameIsbnVO {
        return queryFactory.singleQuery {
            selectMulti(col(Book::name), col(BookMeta::isbn10), col(BookMeta::isbn13))
            from(entity(Book::class))
            associate(Book::meta)
            where(col(Book::id).equal(id))
        }
    }

    suspend fun findAll(spec: FindBookSpec): List<Book> {
        return queryFactory.listQuery {
            select(entity(Book::class))
            from(entity(Book::class))
            where(findSpec(spec))
        }
    }

    suspend fun update(spec: UpdateBookSpec): Int {
        return queryFactory.updateQuery<Book> {
            associate(Book::meta)
            where(findSpec(spec.findBookSpec))
            set(col(Book::name), spec.name)
        }
    }

    suspend fun delete(spec: FindBookSpec): Int {
        return queryFactory.deleteQuery<Book> {
            associate(Book::meta)
            where(findSpec(spec))
        }
    }

    /**
     * if you want reuse Predicates, extract Expressions and make an Extension like this method
     * WhereDsl.xxxx
     */
    private fun WhereDsl.findSpec(spec: FindBookSpec) =
        and(
            col(Book::name).like("%${spec.name}%"),
            spec.publisher?.let { col(BookMeta::publisher).equal(spec.publisher) }
        )

    suspend fun findAll(spec: FindBookSpec, pageable: Pageable): Page<Book> {
        return queryFactory.pageQuery(pageable) {
            select(entity(Book::class))
            from(entity(Book::class))
            where(findSpec(spec))
        }
    }
}