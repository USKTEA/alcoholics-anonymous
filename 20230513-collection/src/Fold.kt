fun main() {
    println(joinToString(listOf(1, 2, 3, 4, 5), ","))
    println(joinToString(listOf(1), ","))
    println(joinToString<Int>(listOf(), ","))
}


fun <T> joinToString(list: List<T>, delimiter: String): String =
    list.foldIndexed(StringBuilder()) { index: Int, acc: StringBuilder, t: T ->
        acc.append(t)

        if (index == list.size - 1) {
            return acc.toString()
        }

        acc.append(delimiter)
    }.toString()
