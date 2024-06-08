package com.redisexample.demo.repository

import com.redisexample.demo.model.Account
import org.springframework.data.repository.CrudRepository

interface AccountRepository : CrudRepository<Account, String>
