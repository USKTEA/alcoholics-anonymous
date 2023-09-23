package com.example.demo.commands

class SimpleCommand(
    private val name: String
): Command {
    override fun execute(): String {
        return name
    }
}
