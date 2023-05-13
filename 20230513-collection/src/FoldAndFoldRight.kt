fun main() {
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    val copied = foldCopy(numbers)
    println(copied)
    println(copied === numbers)

    val revered = reverseCopy(numbers)
    println(revered)
}

fun <T> foldCopy(list: List<T>): List<T> {
    return list.fold(mutableListOf()) { acc: MutableList<T>, t: T ->
        acc.add(t)
        acc
    }
}

fun <T> reverseCopy(list: List<T>): List<T> {
    return list.foldRight(mutableListOf()) { t: T, acc: MutableList<T> ->
        acc.add(t)
        acc
    }
}

