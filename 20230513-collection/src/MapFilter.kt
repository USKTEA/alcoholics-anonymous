// 맵에서 키가 짝수거나 값이 짝수인 항목만 포함시킨 새로운 맵을 반환한다

fun main() {
    val toBe = mapOf(2 to 4, 3 to 4, 4 to 1)
    val origin = mapOf(1 to 1, 2 to 4, 3 to 4, 4 to 1, 5 to 1)

    println(toBe == filterOdds(origin))
}

fun filterOdds(origin: Map<Int, Int>): Map<Int, Int> {
    return origin.filter { it.key % 2 == 0 || it.value % 2 == 0}
}

