class InfInt(private var number: Int = 1) {

    operator fun iterator() = this
    operator fun hasNext(): Boolean {
        return true
    }

    operator fun next(): Int {
        val previous = number;

        number += 1;

        return previous;
    }

    fun number(): Int {
        return number;
    }
}