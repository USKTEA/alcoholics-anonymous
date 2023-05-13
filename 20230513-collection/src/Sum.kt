import kotlin.random.Random

fun main() {
    fun generateNumbers() = IntArray(Random.nextInt(5, 100)) { Random.nextInt(1, 100) }.toList()

    val origin = generateNumbers()

    println(origin)

    println(sum(origin))
    println(reduceSum(origin))
    println(forEachSum(origin))
}

fun sum(numbers: List<Int>): Int {
    return numbers.sum()
}

fun reduceSum(numbers: List<Int>): Int {
    return numbers.reduce { acc: Int, i: Int -> acc + i }
}

fun forEachSum(numbers: List<Int>): Int {
    var result = 0

    numbers.forEach { result += it }

    return result
}