import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class OneCounterTest {
    private val oneCounter = OneCounter

    @Test
    fun count() {
        assertEquals(1, oneCounter.count("10000000"))
    }
}
