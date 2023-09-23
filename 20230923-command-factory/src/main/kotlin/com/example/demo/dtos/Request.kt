package com.example.demo.dtos

import java.lang.RuntimeException


abstract class Request(
    val name: String
) {
    companion object {
        fun from(command: String): Request {
            return when (command) {
                "simple" -> SimpleRequest(command)
                "not simple" -> NotSimpleRequest(command, "foo")
                else -> throw RuntimeException()
            }
        }
    }
}

class SimpleRequest(
    name: String,
): Request(name)

class NotSimpleRequest(
    name: String,
    val foo: String,
): Request(name)
