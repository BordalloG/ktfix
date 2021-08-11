package ktfix.extensions

import ktfix.extensions.RandomExtensions.Companion.nextChar
import ktfix.extensions.RandomExtensions.Companion.nextString
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.random.Random

class RandomExtensionsTest {

    @Test
    fun `nextString extension function should generate random Strings`() {
        val firstValue = Random.nextString(3)
        val secondValue = Random.nextString(4)

        assertNotEquals(firstValue, secondValue)
    }

    @ParameterizedTest()
    @ValueSource(ints = [1, 10, 1000])
    fun `nextString extension function should generate random Strings with desired length`(length: Int) {
        val string = Random.nextString(length)

        assert(string.length == length)
    }

    @ParameterizedTest()
    @ValueSource(ints = [-1, -10, 0])
    fun `negative and zero Length should return an empty string`(length: Int) {
        val string = Random.nextString(length)

        assert(string == "")
    }

    @Test
    fun `nextChat extension function should generate random chars`() {
        val firstValue = Random.nextChar()
        val secondValue = Random.nextChar()

        assertNotEquals(firstValue, secondValue)
    }
}
