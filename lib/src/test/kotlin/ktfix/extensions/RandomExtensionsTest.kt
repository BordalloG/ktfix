package ktfix.extensions

import ktfix.extensions.RandomExtensions.Companion.nextChar
import ktfix.extensions.RandomExtensions.Companion.nextEnum
import ktfix.extensions.RandomExtensions.Companion.nextLocalDate
import ktfix.extensions.RandomExtensions.Companion.nextLocalDateTime
import ktfix.extensions.RandomExtensions.Companion.nextString
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.random.Random

enum class EnumClazz {
    FIRST,
    SECOND,
    THIRD,
    FOURTH,
    FIFTH
}

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
    fun `nextChar extension function should generate random chars`() {
        val firstValue = Random.nextChar()
        val secondValue = Random.nextChar()

        assertNotEquals(firstValue, secondValue)
    }

    @Test
    fun `nextLocalDate extension function should generate random Date`() {
        val firstValue = Random.nextLocalDate()
        val secondValue = Random.nextLocalDate()

        assertNotEquals(firstValue, secondValue)
    }

    @Test
    fun `nextLocalDate extension function should generate random Date passing valid values as start date and end date`() {
        val firstValue = Random.nextLocalDate(
            startDate = LocalDate.of(1998, 10, 21),
            endDate = LocalDate.of(2021, 10, 21),
        )
        val secondValue = Random.nextLocalDate(
            startDate = LocalDate.of(2021, 11, 21),
            endDate = LocalDate.of(2040, 10, 21),
        )

        assertNotEquals(firstValue, secondValue)
        assert(firstValue < secondValue)
    }

    @Test
    fun `nextLocalDate extension function should throws an exception when start date is after end date`() {
        assertThrows<IllegalArgumentException> {
            Random.nextLocalDate(
                LocalDate.of(2021, 1, 1),
                LocalDate.of(2019, 1, 1)
            )
        }
    }

    @Test
    fun `nextLocalDateTime extension function should generate random Date`() {
        val firstValue = Random.nextLocalDateTime()
        val secondValue = Random.nextLocalDateTime()

        assertNotEquals(firstValue, secondValue)
    }

    @Test
    fun `nextLocalDateTime extension function should generate random Date passing valid values as start date and end date`() {
        val firstValue = Random.nextLocalDateTime(
            startDate = LocalDateTime.of(1998, 10, 21, 1, 1, 1),
            endDate = LocalDateTime.of(2021, 10, 21, 1, 1, 1),
        )
        val secondValue = Random.nextLocalDateTime(
            startDate = LocalDateTime.of(2021, 11, 21, 1, 1, 1),
            endDate = LocalDateTime.of(2040, 10, 21, 1, 1, 1),
        )

        assertNotEquals(firstValue, secondValue)
        assert(firstValue < secondValue)
    }

    @Test
    fun `nextLocalDateTime extension function should throws an exception when start date is after end date`() {
        assertThrows<IllegalArgumentException> {
            Random.nextLocalDateTime(
                LocalDateTime.of(2021, 1, 1, 1, 1, 1),
                LocalDateTime.of(2019, 1, 1, 1, 1, 1)
            )
        }
    }

    @Test
    fun `nextEnum should return a random value from that Enum`() {
        val randomValue = Random.nextEnum(EnumClazz::class)
        assert(randomValue in EnumClazz.values())
    }
}
