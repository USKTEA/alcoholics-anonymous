fun main() {
    val origin = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val toBe = listOf(1, 3, 5, 7, 9)

    println(toBe == toList(origin))
}

fun toList(origin: Array<Int>) : List<Int> {
    return origin.filterIndexed { index: Int, i: Int -> index == 0 || i % 2 != 0 }
}