class CustomTriple<T>(val first: T, val second: T, val third: T) {
    override fun toString(): String {
        return "($first, $second, $third)"
    }

    fun reverse(): CustomTriple<T> {
        return CustomTriple(third, second, first)
    }
}