package com.redisexample.demo.controller

import com.redisexample.demo.model.Account
import com.redisexample.demo.repository.AccountRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/accounts")
class AccountController(
    private val accountRepository: AccountRepository,
) {

    @GetMapping
    fun countAccounts(): Long {
        return accountRepository.count()
    }

    @GetMapping("/{name}")
    fun getAccount(
        @PathVariable name: String
    ): Account {
        return accountRepository.findByName(name).orElseThrow()
    }

    @PostMapping("/{name}")
    fun createAccount(
        @PathVariable name: String
    ): Account {
        val count = accountRepository.count()

        return accountRepository.save(
            Account(
                id = count,
                name = name,
            )
        )
    }
}
