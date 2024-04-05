package com.usktea.elasitc.repository

import com.usktea.elasitc.document.Book
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface BookRepository : ElasticsearchRepository<Book, String>
