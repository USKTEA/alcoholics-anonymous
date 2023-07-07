package com.oop.sample.sample.utils

interface Calculable {
    fun calculate(operator: String, firstOperand: Int, secondOperand: Int): Int
    fun add(firstOperand: Int, secondOperand: Int): Int
    fun minus(firstOperand: Int, secondOperand: Int): Int
    fun multiply(firstOperand: Int, secondOperand: Int): Int
    fun divide(firstOperand: Int, secondOperand: Int): Int
}