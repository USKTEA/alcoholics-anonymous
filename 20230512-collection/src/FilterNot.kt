val a = arrayOf(1, 2, 3, 4, 5).filter { it % 2 == 0 }
val b = arrayOf(1, 2, 3, 4, 5).filterNot { it % 2 != 0 }

val c = listOf(1, 2, 3, 4, 5).filter { it % 2 == 0 }
val d = listOf(1, 2, 3, 4, 5).filterNot { it % 2 != 0 }

val e = setOf(1, 2, 3, 4, 5).filter { it % 2 == 0 }
val f = setOf(1, 2, 3, 4, 5).filterNot { it % 2 != 0 }
fun main() {
    println(a)
    println(b)

    println(c)
    println(d)

    println(e)
    println(f)

// -------------------
}

