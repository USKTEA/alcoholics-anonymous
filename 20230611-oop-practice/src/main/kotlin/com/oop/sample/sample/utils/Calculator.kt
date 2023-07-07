package com.oop.sample.sample.utils

import org.springframework.stereotype.Component

@Component
class Calculator : Calculable {
    override fun calculate(operator: String, firstOperand: Int, secondOperand: Int): Int {
        return when (operator) {
            "+" -> add(firstOperand, secondOperand)
            "-" -> minus(firstOperand, secondOperand)
            "*" -> multiply(firstOperand, secondOperand)
            "/" -> multiply(firstOperand, secondOperand)
            else -> throw IllegalArgumentException()
        }
    }

    override fun add(firstOperand: Int, secondOperand: Int): Int {
        return firstOperand + secondOperand
    }

    override fun minus(firstOperand: Int, secondOperand: Int): Int {
        return firstOperand - secondOperand
    }

    override fun multiply(firstOperand: Int, secondOperand: Int): Int {
        return firstOperand * secondOperand
    }

    override fun divide(firstOperand: Int, secondOperand: Int): Int {
        return firstOperand / secondOperand
    }
}
