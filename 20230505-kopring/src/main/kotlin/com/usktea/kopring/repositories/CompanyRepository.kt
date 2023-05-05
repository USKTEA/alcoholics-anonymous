package com.usktea.kopring.repositories

import com.usktea.kopring.models.Company
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CompanyRepository: CoroutineCrudRepository<Company, Long> {

    fun findByNameContaining(name: String): Flow<Company>
}