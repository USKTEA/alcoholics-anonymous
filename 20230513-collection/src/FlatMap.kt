fun main() {
    val man = Book(author = "man", 1000L)
    val me = Book(author = "me", 1000L)
    val you = Book(author = "you", 2000L)
    val our = Book(author = "our", 2000L)
    val your = Book(author = "your", 2000L)

    val all = listOf(man, me, you, our, your)

    val authors = flatMap(all) { listOf(it.author) }

    println(authors == listOf("man", "me", "you", "our", "your"))
}

fun <T, R> flatMap(all: List<T>, function: (T) -> List<R>): List<R> {
    return all.map { function(it) }.flatten()
}

class Book(var author: String, private val price: Long)