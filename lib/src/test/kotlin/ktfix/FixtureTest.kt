package ktfix

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.time.LocalDate
import java.time.LocalDateTime

data class BasicTypes(
    val string: String,
    val char: Char,
    val double: Double,
    val float: Float,
    val int: Int,
    val short: Short,
    val long: Long,
    val byte: Byte,
    val boolean: Boolean
)

enum class EnumClazz {
    FIRST,
    SECOND,
    THIRD,
    FOURTH,
    FIFTH
}

data class Clazz(val double: Double, val string: String, val int: Int)
data class ClazzWithClass(val integer: Int, val clazz: Clazz)
data class ClazzWithDate(val localDateTime: LocalDateTime, val localDate: LocalDate)
data class ClazzWithEnum(val enumClass: EnumClazz)

class FixtureTest {

    @Test
    fun `should build a fixture of a primitive types`() {
        assertDoesNotThrow { println(Fixture.build<Int>())}
        assertDoesNotThrow { println(Fixture.build<String>())}
        assertDoesNotThrow { println(Fixture.build<Double>())}
    }

    @Test
    fun `should build a fixture of a class with only primitive types`() {
        assertDoesNotThrow { println("Generated: ${Fixture.build<BasicTypes>()}") }
    }

    @Test
    fun `should build a fixture of a class with another class`() {
        assertDoesNotThrow { Fixture.build<ClazzWithClass>() }
    }

    @Test
    fun `should build a fixture with date and local date properties`() {
        assertDoesNotThrow { Fixture.build<ClazzWithDate>() }
    }

    @Test
    fun `should build a fixture with enum properties`() {
        assertDoesNotThrow {
            val value = Fixture.build<ClazzWithEnum>()
            assert(value.enumClass in EnumClazz.values())
        }
    }
}
