package com.usktea.todolistreactive.applications

import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.linecorp.kotlinjdsl.querydsl.from.associate
import com.linecorp.kotlinjdsl.querydsl.where.WhereDsl
import com.linecorp.kotlinjdsl.spring.data.reactive.query.*
import com.usktea.todolistreactive.entity.*
import io.smallrye.mutiny.coroutines.awaitSuspending
import org.hibernate.reactive.mutiny.Mutiny
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ReactiveBookService(
    private val sessionFactory: Mutiny.SessionFactory,
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
