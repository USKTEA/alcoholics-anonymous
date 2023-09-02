import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BinaryConvertorTest {
    private val binaryConvertor = BinaryConvertor

    @Test
    fun convert1() {
        assertEquals("1", binaryConvertor.convert(1))
    }

    @Test
    fun convert2() {
        assertEquals("10", binaryConvertor.convert(2))
    }

    @Test
    fun convert3() {
        assertEquals("11", binaryConvertor.convert(3))
    }

    @Test
    fun convert4() {
        assertEquals("100", binaryConvertor.convert(4))
    }
}
