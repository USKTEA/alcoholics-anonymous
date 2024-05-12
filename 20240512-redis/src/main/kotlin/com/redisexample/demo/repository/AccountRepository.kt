package com.redisexample.demo.repository

import com.redisexample.demo.model.Account
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface AccountRepository : CrudRepository<Account, Long> {
    fun findByName(name: String): Optional<Account>
}
