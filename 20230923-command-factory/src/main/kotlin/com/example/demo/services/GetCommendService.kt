package com.example.demo.services

import com.example.demo.commands.Command
import com.example.demo.commands.NotSimpleCommand
import com.example.demo.commands.SimpleCommand
import com.example.demo.dtos.Request
import org.springframework.stereotype.Service

@Service
class GetCommendService(
    private val commands: MutableMap<String, (request: Request) -> Command>,
    private val addPlusService: AddPlusService,
    private val addMinusService: AddMinusService,
) {
    init {
        commands["simple"] = { request: Request -> SimpleCommand(request.name) }
        commands["not simple"] = { request: Request -> NotSimpleCommand(request, addPlusService, addMinusService) }
    }

    fun get(name: String): (request: Request) -> Command {
        return commands[name] ?: throw RuntimeException()
    }
}


