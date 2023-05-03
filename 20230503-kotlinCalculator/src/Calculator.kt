class Calculator {
    companion object {
        val divide = { x: Int, y: Int -> x / y }
        val multiply = { x: Int, y: Int -> x * y }
        val add = { x: Int, y: Int -> x + y }
        val minus = { x: Int, y: Int -> x - y }
    }

    fun calculate(x: Int, operator: Char, y: Int): Int {
        return when (operator) {
            '+' -> add(x, y)
            '-' -> minus(x, y)
            '*' -> multiply(x, y)
            '/' -> divide(x, y)
            else -> throw IllegalArgumentException()
        }
    }
}