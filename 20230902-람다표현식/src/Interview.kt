// 정수형 숫자가 주어졌을 때, 해당 숫자의 이진수 표현에서 1의 갯수를 구해라
// 0, 1, 10, 11, 100, 101

// someFunction(1) = 1
// someFunction(0) = 0
// someFunction(2) = 1
// someFunction(3) = 2
// someFunction(4) = 1
// someFunction(5) = 2

fun someFunction(number: Int): Int {
    val binaries = convert(number, BinaryConvertor)

    return count(binaries, OneCounter)
}

fun count(word: String, counter: Counter): Int {
    return counter.count(word)
}

fun convert(number: Int, convertor: Convertor): String {
    return convertor.convert(number)
}

interface Convertor {
    fun convert(number: Int): String
}

interface Counter {
    fun count(word: String): Int
}

object OneCounter: Counter {
    override fun count(word: String): Int {
        return word.count { it == '1' }
    }
}

object BinaryConvertor: Convertor {
    override fun convert(number: Int): String {
        val stringBuilder = StringBuilder()
        var toConvert = number

        if (number == 0) {
            return "0"
        }

        while (toConvert >= 1) {
            stringBuilder.append(toConvert % 2)
            toConvert /= 2
        }

        stringBuilder.append(toConvert)

        return stringBuilder.toString().reversed()
    }
}
