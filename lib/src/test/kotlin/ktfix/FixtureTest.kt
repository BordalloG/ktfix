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
        assert(basic.float != null)
        assert(basic.short != null)
        assert(basic.long != null)
        assert(basic.boolean != null)
    }
}