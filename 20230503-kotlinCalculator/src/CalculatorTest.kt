import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CalculatorTest {
    @Test
    fun add() {
        val add = Calculator.add

        assertEquals(4, add(3, 1))
    }

    @Test
    fun minus() {
        val minus = Calculator.minus

        assertEquals(5, minus(7, 2))
    }

    @Test
    fun multiply() {
        val multiply = Calculator.multiply

        assertEquals(10, multiply(2, 5))
    }

    @Test
    fun divide() {
        val divide = Calculator.divide

        assertEquals(5, divide(10, 2))
    }

    @Test
    fun calculate() {
        val calculator = Calculator()

        assertEquals(10, calculator.calculate(1, '+', 9))
        assertEquals(10, calculator.calculate(11, '-', 1))
        assertEquals(11, calculator.calculate(11, '*', 1))
        assertEquals(3, calculator.calculate(6, '/', 2))
    }
}