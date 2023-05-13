fun main() {
    val a = listOf(1, 2, 3, 4, 5)
    val b = listOf(1, 2, 2, 3, 4, 5)
    val c = listOf(2, 3, 1, 2, 3, 4, 5, 2)

    println(stat(c))
}

fun <T> stat(list: List<T>): Map<T, Int> {
    return list.fold(mutableMapOf()) {acc: MutableMap<T, Int>, t: T ->
        acc[t] = acc[t]?.let { it + 1 } ?: 1

        acc
    }
}