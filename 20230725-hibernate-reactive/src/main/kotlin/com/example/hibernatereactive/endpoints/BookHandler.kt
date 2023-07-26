package com.example.hibernatereactive.endpoints

import com.example.hibernatereactive.models.Author
import com.example.hibernatereactive.models.Book
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence.createEntityManagerFactory
import org.hibernate.reactive.stage.Stage
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import java.time.LocalDate
import java.util.Calendar.*


@Component
class BookHandler {

    suspend fun getAll(request: ServerRequest): ServerResponse {
        val entityManagerFactory: EntityManagerFactory = createEntityManagerFactory("postgresql-example")
        val session: Stage.SessionFactory = entityManagerFactory.unwrap(Stage.SessionFactory::class.java)

        val author1 = Author(name = "Iain M. Banks")
        val author2 = Author(name = "Neal Stephenson")
        val book1 = Book(
            isbn = "1-85723-235-6",
            title = "Feersum Endjinn",
            author = author1,
            published = LocalDate.of(1994, JANUARY + 1, 1)
        )
        val book2 = Book(
            isbn = "0-380-97346-4",
            title = "Cryptonomicon",
            author = author2,
            published = LocalDate.of(1999, MAY + 1, 1)
        )
        val book3 = Book(
            isbn = "0-553-08853-X",
            title = "Snow Crash",
            author = author2,
            published = LocalDate.of(1992, JUNE + 1, 1)
        )

        author1.addBook(book1)
        author2.addBook(book2)
        author2.addBook(book3)

        session.use { session ->
            session.withTransaction { session ->
                session.persist(author1, author2)
            }.toCompletableFuture().join()

            session.withSession { session ->
                session.find(Book::class.java, book1.id())
                    .thenAccept { book -> println(book.title() + "is a great book!") }
            }.toCompletableFuture().join()
        }

        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait("ok")
    }
}