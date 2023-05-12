fun main() {
    val firstAPos = findFirstIndex("I am a boy", "a")
    val lastAPos = findLastIndex("I am a boy", "a")

    println(firstAPos == 2)
    println(lastAPos == 5)
}

fun findFirstIndex(word: String, letter: String): Int {
    return word.indexOf(letter)
}

fun findLastIndex(word: String, letter: String): Int {
    return word.lastIndexOf(letter)
}
