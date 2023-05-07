import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CustomTripleTest {
    @Test
    fun acceptThreeSameTypeElements() {
        val triple = CustomTriple(1, 2, 3)
    }

    @Test
    fun acceptDifferentTypeElements() {
        val triple = CustomTriple("first", 2, 3.0)
    }

    @Test
    fun getSameTypeElements() {
        val triple = CustomTriple(1, 2, 3)

        assertEquals(1, triple.first)
        assertEquals(2, triple.second)
        assertEquals(3, triple.third)
    }

    @Test
    fun getDifferentTypeElements() {
        val triple = CustomTriple("first", 2, 3.0)

        assertEquals("first", triple.first)
        assertEquals(2, triple.second)
        assertEquals(3.0, triple.third)
    }

    @Test
    fun string() {
        val tripe1 = CustomTriple(1, 2, 3)
        val tripe2 = CustomTriple("first", 2, 3.0)

        assertEquals("(1, 2, 3)", tripe1.toString())
        assertEquals("(first, 2, 3.0)", tripe2.toString())
    }

    @Test
    fun reverse() {
        val tripe1 = CustomTriple(1, 2, 3)
        val tripe2 = CustomTriple("first", 2, 3.0)

        val reversed1 = tripe1.reverse()
        val reversed2 = tripe2.reverse()

        assertEquals("(3, 2, 1)", reversed1.toString())
        assertEquals("(3.0, 2, first)", reversed2.toString())
    }
}
