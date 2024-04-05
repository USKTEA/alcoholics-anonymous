package com.usktea.elasitc.repository

import com.usktea.elasitc.document.Food
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface FoodRepository : ElasticsearchRepository<Food, String>
