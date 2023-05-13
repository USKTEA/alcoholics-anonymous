fun main() {
    val origin1 = listOf(1, 2, 3, 4, 5)
    val origin2 = listOf(1, 2, 2, 3, 4, 5)
    val origin3 = listOf(2, 3, 1, 2, 3, 4, 5, 2)

    println(removeDuplicate(origin1).sortedDescending())
    println(removeDuplicate(origin2).sortedDescending())
    println(removeDuplicate(origin3).sortedDescending())
}

fun <T> removeDuplicate(list: List<T>): List<T> {
    return list.fold(mutableListOf()) {acc: MutableList<T>, t: T ->
        if (!acc.contains(t)) {
            acc.add(t)
        }

        acc
    }
}
