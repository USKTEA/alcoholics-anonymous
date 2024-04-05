package com.usktea.elasitc.controller

import co.elastic.clients.util.DateTime
import com.usktea.elasitc.document.Book
import com.usktea.elasitc.document.Food
import com.usktea.elasitc.repository.BookRepository
import com.usktea.elasitc.repository.FoodRepository
import org.springframework.data.elasticsearch.client.elc.NativeQuery
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.SearchHits
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.*
import java.time.OffsetDateTime
import java.util.*

@RestController
@RequestMapping("/elastic")
class ElasticController(
    private val bookRepository: BookRepository,
    private val foodRepository: FoodRepository,
    private val elasticsearchOperations: ElasticsearchOperations,
) {

    @PostMapping("/insert")
    fun insert() {
        val books = listOf(
            Book(UUID.randomUUID().toString(), "수학책", "이거슨 좋은 수학", 1000),
            Book(UUID.randomUUID().toString(), "과학책", "여러분 신비한 과학", 1500),
            Book(UUID.randomUUID().toString(), "영어책", "영국과 미국은 영어로 대화한다", 1000),
            Book(UUID.randomUUID().toString(), "코딩책", "객체지향의 진수", 3000)
        )


        bookRepository.saveAll(books)
    }

    @PostMapping("/foods")
    fun insertFoods() {
        val foods = listOf(
            Food(id = UUID.randomUUID().toString(), name = "닭발", createdAt = Date()),
            Food(id = UUID.randomUUID().toString(), name = "달콤한 초콜릿", createdAt = Date()),
            Food(id = UUID.randomUUID().toString(), name = "치킨", createdAt = Date()),
            Food(id = UUID.randomUUID().toString(), name = "피자", createdAt = Date()),
            Food(id = UUID.randomUUID().toString(), name = "달콤한 사탕", createdAt = Date()),
            Food(id = UUID.randomUUID().toString(), name = "아메리카노", createdAt = Date()),
            Food(id = UUID.randomUUID().toString(), name = "시원한 커피", createdAt = Date()),
            Food(id = UUID.randomUUID().toString(), name = "라떼", createdAt = Date()),
            Food(id = UUID.randomUUID().toString(), name = "감자튀김", createdAt = Date()),
            Food(id = UUID.randomUUID().toString(), name = "감튀", createdAt = Date()),
            Food(id = UUID.randomUUID().toString(), name = "케이크", createdAt = Date()),
            Food(id = UUID.randomUUID().toString(), name = "쿠키", createdAt = Date())
        )

        foodRepository.saveAll(foods)
    }

    @GetMapping("/foods")
    fun books(
        @RequestParam params: MultiValueMap<String, Any?>
    ): SearchResultDto<Food> {
        val a = params["name"]?.first().toString()

        val query = NativeQuery.builder()
            .withQuery { q ->
                q.match { m ->
                    m.field("name").query(a)
                }
            }.build()

        println(query)
        val searchHits = elasticsearchOperations.search(query, Food::class.java)

        return SearchResultDto.fromSearchHits(searchHits, 0)
    }
}

class SearchResultDto<T>(
    val content: List<T> = emptyList(),
    val totalElements: Long = 0L,
    val searchAfter: List<String> = emptyList()
) {

    companion object {
        fun <T> fromSearchHits(searchHits: SearchHits<T>, pageSize: Int): SearchResultDto<T> {
            return SearchResultDto(
                content = searchHits.searchHits.map { it.content },
                totalElements = searchHits.totalHits,
                searchAfter = getSearchAfter(searchHits, pageSize),
            )
        }


        private fun <T> getSearchAfter(searchHits: SearchHits<T>, pageSize: Int): List<String> {
            if (searchHits.searchHits.size < pageSize) {
                return emptyList()
            }

            return searchHits.lastOrNull()
                ?.sortValues
                ?.map { it.toString() }
                ?: emptyList()
        }
    }
}
