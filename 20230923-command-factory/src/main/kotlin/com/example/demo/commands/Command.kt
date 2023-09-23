package com.example.demo.commands

sealed interface Command {
    fun execute(): String
}
