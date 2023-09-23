package com.example.demo.commands

import com.example.demo.dtos.NotSimpleRequest
import com.example.demo.dtos.Request
import com.example.demo.services.AddMinusService
import com.example.demo.services.AddPlusService

class NotSimpleCommand(
    private val request: Request,
    private val plusService: AddPlusService,
    private val minusService: AddMinusService,
): Command {
    override fun execute(): String {
        val changed = request as NotSimpleRequest
        val added = plusService.add(changed.name)

        return minusService.add(added)
    }
}
