fun main() {
    val snake = "i_am_snake"
    val camel = "IAmSnake"

    println(toCamel(snake))

    println(toCamel(snake) == camel)
}

fun toCamel(word: String): String {
    return word.split("_")
        .map{ "${it[0].uppercaseChar()}${it.subSequence(1, it.length)}"  }
        .joinToString("")
}