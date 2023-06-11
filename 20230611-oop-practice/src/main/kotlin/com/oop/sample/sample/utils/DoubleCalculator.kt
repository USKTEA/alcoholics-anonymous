package com.oop.sample.sample.utils

class DoubleCalculator: Calculable {
    fun calculate(operator: String, firstOperand: Int, secondOperand: Int): Int {
        return when (operator) {
            "+" -> add(firstOperand, secondOperand)
            "-" -> minus(firstOperand, secondOperand)
            "*" -> multiply(firstOperand, secondOperand)
            "/" -> multiply(firstOperand, secondOperand)
            else -> throw IllegalArgumentException()
        }
    }

    override fun add(firstOperand: Int, secondOperand: Int): Int {
        return (firstOperand + secondOperand) * 2
    }

    override fun minus(firstOperand: Int, secondOperand: Int): Int {
        return (firstOperand - secondOperand) * 2
    }

    override fun multiply(firstOperand: Int, secondOperand: Int): Int {
        return (firstOperand * secondOperand) * 2
    }

    override fun divide(firstOperand: Int, secondOperand: Int): Int {
        return (firstOperand / secondOperand) * 2
    }
}