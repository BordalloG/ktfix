package ktfix

import org.junit.Test

data class BasicTypes(val string:String, val char: Char,
                      val double: Double, val float: Float,
                      val int: Int, val short:Short, val long: Long,
                      val byte: Byte,
                      val boolean: Boolean
                )

class FixtureTest {

    @Test fun `should build a fixture of a class with all primitive types`(){
        val basic = Fixture.build<BasicTypes>()

        assert(basic.string != null)
        assert(basic.char != null)
        assert(basic.double != null)
        assert(basic.float != null)
        assert(basic.int != null)
        assert(basic.short != null)
        assert(basic.long != null)
        assert(basic.byte != null)
        assert(basic.boolean != null)
    }

    @Test fun `should build a fixture receiving default values`(){
        val defaults = mutableMapOf<String, Any>(
            ("string" to "cool string"),
            ("char" to 'h'),
            ("double" to 0.123),
            ("float" to 0.123f),
            ("int" to 2),
            ("short" to 2.toShort()),
            ("long" to 3L),
            ("byte" to 2),
            ("boolean" to false)
            )

        val basic = Fixture.build<BasicTypes>(defaults)

        assert(basic.string != null && basic.string == "cool string")
        assert(basic.char != null && basic.char == 'h')
        assert(basic.double != null && basic.double == 0.123)
        assert(basic.float != null && basic.float == 0.123f)
        assert(basic.int != null && basic.int == 2)
        assert(basic.short != null && basic.short == 2.toShort())
        assert(basic.long != null && basic.long ==  3L)
        assert(basic.boolean != null && !basic.boolean)
    }
}