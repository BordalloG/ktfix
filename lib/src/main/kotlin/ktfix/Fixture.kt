package ktfix

import ktfix.extensions.RandomExtensions
import ktfix.strategies.BasicTypeGenerator
import ktfix.strategies.ComplexObjectGenerator
import ktfix.strategies.ObjectGenerator
import kotlin.reflect.KClass
import kotlin.reflect.full.createType

class Fixture {

    fun chooseStrategy(clazz: KClass<*>): ObjectGenerator {
        if (clazz.createType().classifier in RandomExtensions.supportedTypes)
            return BasicTypeGenerator()
        return ComplexObjectGenerator()
    }

    companion object {
        val fixture = Fixture()
        inline fun <reified T> build(): T {
            val strategy = fixture.chooseStrategy(T::class)
            return strategy.execute(T::class) as T
        }
    }
}
