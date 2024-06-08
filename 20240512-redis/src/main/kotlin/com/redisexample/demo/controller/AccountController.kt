package com.redisexample.demo.controller

import com.redisexample.demo.model.Account
import com.redisexample.demo.repository.AccountRepository
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/accounts")
class AccountController(
    private val accountRepository: AccountRepository,
) {

    @GetMapping
    fun countAccounts(): Long {
        val before = System.currentTimeMillis()

        accountRepository.count()

        val after = System.currentTimeMillis()

        return after - before
    }

    @GetMapping("/{id}")
    fun getAccount(
        @PathVariable id: String
    ): Account {
        return accountRepository.findById(id).orElseThrow()
    }

    @PostMapping
    fun createAccount(): Long {
        val before = System.currentTimeMillis()

        val result = accountRepository.save(Account(name = "wowwow"))

        val after = System.currentTimeMillis()

        return after - before
    }

    @PostMapping("/bulk")
    fun createAccountBulk(): String {

        for (i: Int in 0..6200000) {
            accountRepository.save(
                Account(
                    name = "channel-post-create-player-$i-null-someId"
                )
            )
        }

        return "ok"
    }
}
