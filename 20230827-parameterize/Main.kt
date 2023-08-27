fun main(args: Array<String>) {
    val apples = listOf(
        Apple(weigth = 100, color = "RED"),
        Apple(weigth = 100, color = "RED"),
        Apple(weigth = 120, color = "RED"),
        Apple(weigth = 120, color = "RED"),
        Apple(weigth = 100, color = "GREEN"),
        Apple(weigth = 100, color = "GREEN"),
        Apple(weigth = 120, color = "GREEN"),
        Apple(weigth = 120, color = "GREEN"),
    )

    val result1 = apples.filter { apple -> AppleGreenColorPredicate().test(apple) }
        .filter { apple -> AppleWeigthPredicate(weight = 100).test(apple) }

    val result2 = apples.filter { apple -> apple.color == "RED" && apple.weigth > 100 }
}


data class Apple(
    val weigth: Int,
    val color: String
)

interface ApplePredicate {
    fun test(apple: Apple): Boolean
}

class AppleGreenColorPredicate : ApplePredicate {
    override fun test(apple: Apple): Boolean {
        return apple.color == "GREEN"
    }
}

class AppleRedColorPredicate : ApplePredicate {
    override fun test(apple: Apple): Boolean {
        return apple.color == "RED"
    }
}

class AppleWeigthPredicate(
    val weight: Int
) : ApplePredicate {
    override fun test(apple: Apple): Boolean {
        return apple.weigth > weight
    }
}

