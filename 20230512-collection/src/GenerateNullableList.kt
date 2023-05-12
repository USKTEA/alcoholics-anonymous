import kotlin.random.Random

fun generateNullableList() = IntArray(Random.nextInt(5, 100)) { Random.nextInt(1, 110) }
    .toList()
    .map { if (it > 100) null else it }

fun main() {
    val a = generateNullableList().filter { it != null && it >= 20 && it <= 50 && it % 7 == 0 }.sumOf { it ?: 0 }
    val b = generateNullableList().filterNotNull().filter { it in 20..50 && it % 7 == 0 }.sum()
}