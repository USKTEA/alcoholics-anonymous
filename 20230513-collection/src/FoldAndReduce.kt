fun main() {
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    val length1 = lengthFold(numbers)
    val length2 = lengthReduce(numbers)

    println(length1 == 10)
    println(length2 == 10)

    val max1 = maxFold(numbers)
    val max2 = maxReduce(numbers)

    println(max1 == 10)
    println(max2 == 10)
}

fun lengthFold(list: List<Int>): Int {
    return list.fold(0) { acc: Int, _: Int -> acc + 1 }
}

fun lengthReduce(list: List<Int>): Int {
    return list.reduce { acc: Int, _: Int -> acc + 1 }
}

fun maxFold(list: List<Int>): Int {
    return list.fold(list[0]) { acc: Int, i: Int -> if (acc < i) i else acc }
}

fun maxReduce(list: List<Int>): Int {
    return list.reduce { acc: Int, i: Int -> if (acc < i) i else acc }
}