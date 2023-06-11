package com.oop.sample.sample.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CalculatorTest {
    @Test
    fun add() {
        val calculator = Calculator()

        val result = calculator.add(1, 2)

        assertThat(result).isEqualTo(3)
    }

    @Test
    fun minus() {
        val calculator = Calculator()

        val result = calculator.minus(3, 2)

        assertThat(result).isEqualTo(1)
    }

    @Test
    fun multiply() {
        val calculator = Calculator()

        val result = calculator.multiply(3, 2)

        assertThat(result).isEqualTo(6)
    }

    @Test
    fun divide() {
        val calculator = Calculator()

        val result = calculator.divide(4, 2)

        assertThat(result).isEqualTo(2)
    }

    @Test
    fun calculate1() {
        val calculator = Calculator()

        val result = calculator.calculate(operator = "*", firstOperand = 2, secondOperand = 3)

        assertThat(result).isEqualTo(6)
    }

    @Test
    fun calculate2() {
        val calculator = Calculator()

        val result = calculator.calculate(operator = "+", firstOperand = 222, secondOperand = 3)

        assertThat(result).isEqualTo(225)
    }
}
