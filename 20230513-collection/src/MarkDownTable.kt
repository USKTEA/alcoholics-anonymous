fun markDownTable(lists: List<List<String>>): String {
    return lists.foldIndexed(StringBuilder()) {
        index: Int, acc: StringBuilder, strings: List<String> ->
        if (index == 0) {
            acc.append("/*")
        }

        acc.append("\n")

        if (index == 1) {
            strings.forEachIndexed { index: Int, s: String ->
                if (index == 0) {
                    acc.append("|")
                }

                for (i in 0..s.length + 1) {
                    acc.append("-")
                }

                acc.append("|")
            }

            acc.append("\n")
        }

        strings.forEachIndexed { index: Int, s: String ->
            if (index == 0) {
                acc.append("| $s |" )
            }

            if (index != 0 && index != strings.size - 1) {
                acc.append(" $s ")

            }

            if (index == strings.size - 1) {
                acc.append("| $s |")
            }
        }


        if (index == lists.size - 1 ) {
            acc.append("\n")
            acc.append("*/")
        }
        acc
    }.toString()
}

fun main() {
    val answer = """
        /*
        | 1 | 2 | 3 |
        |---|---|---|
        | 4 | 5 | 6 |
        | 7 | 8 | 9 |
        */
    """.trimIndent()

    val lists = listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9))
    val strings = lists.map { it -> it.map { it.toString() } }
    val result = markDownTable(strings)

    println(result)
    println(answer == result)
}