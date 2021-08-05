package ktfix

import org.junit.Test

data class BasicTypes(
    val string: String, val char: Char,
    val double: Double, val float: Float,
    val int: Int, val short: Short, val long: Long,
    val byte: Byte,
    val boolean: Boolean,
    val anyStuff: AnyStuff
)
data class AnyStuff(val any: Double, val laranja: String, val princesa: Int)
class FixtureTest {

    @Test
    fun `should build a fixture of a class with all primitive types`() {
        val basic = Fixture.build<BasicTypes>()
    }

    @Test
    fun `should build a fixture receiving default values`() {
        val defaults = mutableMapOf<String, Any>(
            ("string" to "cool string"),
            ("char" to 'h'),
            ("double" to 0.123),
            ("float" to 0.123f),
            ("int" to 2),
            ("short" to 2.toShort()),
            ("long" to 3L),
            ("byte" to 2),
            ("boolean" to false),
            ("anyStuff" to AnyStuff(0.3, "laranja", 2))
        )

        val basic = Fixture.build<BasicTypes>(defaults)


        assert(basic.string == "cool string")
        assert(basic.char == 'h')
        assert(basic.double == 0.123)
        assert(basic.float == 0.123f)
        assert(basic.int == 2)
        assert(basic.short == 2.toShort())
        assert(basic.long == 3L)
        assert(!basic.boolean)
        assert(basic.anyStuff.laranja == "laranja")
    }
}