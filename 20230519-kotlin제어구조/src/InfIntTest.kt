import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class InfIntTest {

    @Test
    fun `hasNext함수는 항상 true를 반환한다`() {
        val infInt = InfInt()

        for (i in 1..100) {
            assertTrue(infInt.hasNext())
        }
    }

    @Test
    fun `InfInt는 선언될 때 정수값 1을 가진 number field를 가진다`() {
        val infInt = InfInt()

        assertEquals(1, infInt.number())
    }

    @Test
    fun `InfInt의 next는 호출될 때 마다 1만큼 증가한 숫자를 반환한다`() {
        val infInt = InfInt()

        for (i in 1..0) {
            assertEquals(i, infInt.next())
        }
    }

    @Test
    fun `1000번 next의 값을 더한 값은 500500이 되어야 한다`() {
        val infInt = InfInt()
        var sum = 0

        for (x in infInt) {
            if (x > 1000) {
                break
            }

            sum += x
        }

        assertEquals(500500, sum)

        sum = 0

        for (x in infInt) {
            if (x > 1000) {
                break
            }

            sum += x
        }

        println(sum)

        println(1..10)
        println((1..10).step(3).joinToString())
        println((1..10).step(5).joinToString())
        println((1..10).step(5).step(1).joinToString())
        println((1..10).step(5).step(1).reversed().step(5).step(1).joinToString())
        println((1..10).reversed().step(5).step(1).joinToString())
        println((10 downTo 2).joinToString())
        println((10 downTo 2).step(5).joinToString())
        println((10 downTo 2).step(5).reversed().step(5).step(1).joinToString())
        println((1..10).step(4).reversed().joinToString())
    }
}