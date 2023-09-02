fun main() {
    val colorFactory: TriFunction<Int, Int, Int, Color> = TriFunction { t, u, v -> Color(t, u, v) }
    val white = colorFactory.apply(1, 1, 1)

    val inventory = listOf(Apple(30), Apple(20), Apple(10))
    val sorted = inventory.sortedWith(Comparator.comparing(Apple::weight))
    val sorted2 = inventory.sortedBy { apple -> apple.weight }
}

fun <T> pipe(vararg function: (T) -> T): (T) -> T {
    return function.reduce { acc, function -> { t: T -> function(acc(t)) } }
}

data class Color(
    val red: Int,
    val green: Int,
    val blue: Int
)

data class Apple(
    val weight: Int
)

fun interface TriFunction<T, U, V, R> {
    fun apply(t: T, u: U, v: V): R
}
