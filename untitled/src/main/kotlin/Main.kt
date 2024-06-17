fun main(args: Array<String>) {
    val vNumber = Val<Number>(10.0)

    val vAny: Val<Any> = vNumber
    val vInt: Val<Int> = vNumber
}

class Val<out T>(v: T) {
    val v: T = v
}
