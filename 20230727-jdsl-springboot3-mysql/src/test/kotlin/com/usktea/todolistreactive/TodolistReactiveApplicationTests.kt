package com.usktea.todolistreactive

import com.usktea.todolistreactive.entity.BookMeta
import com.usktea.todolistreactive.entity.CreateBookSpec
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest
@AutoConfigureWebTestClient(timeout = "100000")
class TodolistReactiveApplicationTests {

	@Autowired
	private lateinit var client: WebTestClient

	private val context = "/api/v1/books"

	@Test
	fun createBook() {
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

		client.post().uri(context)
			.bodyValue(spec)
			.exchange()
			.expectStatus().isOk
			.returnResult(Long::class.java)
			.responseBody
			.blockFirst()!!
	}
}
