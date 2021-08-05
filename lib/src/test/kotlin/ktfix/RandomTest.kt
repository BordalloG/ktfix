package ktfix

import org.junit.Test
import kotlin.reflect.full.createType
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class RandomTest {

    @Test
    fun `should generate random values of string`() {
        val firstValue = Random.random(String::class.createType())
        val secondValue = Random.random(String::class.createType())

        assertTrue { firstValue is String }.also { secondValue is String }
        assertNotEquals(firstValue, secondValue)
    }
}