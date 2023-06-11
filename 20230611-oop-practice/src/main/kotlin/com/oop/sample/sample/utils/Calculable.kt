package com.oop.sample.sample.utils

interface Calculable {
    fun add(firstOperand: Int, secondOperand: Int): Int
    fun minus(firstOperand: Int, secondOperand: Int): Int
    fun multiply(firstOperand: Int, secondOperand: Int): Int
    fun divide(firstOperand: Int, secondOperand: Int): Int
}