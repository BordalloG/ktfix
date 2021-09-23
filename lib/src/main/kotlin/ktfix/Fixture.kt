package ktfix

import ktfix.extensions.RandomExtensions
import ktfix.strategies.BasicTypeGenerator
import ktfix.strategies.ComplexObjectGenerator
import ktfix.strategies.EnumGenerator
import ktfix.strategies.ObjectGenerator
import kotlin.reflect.KClass
import kotlin.reflect.full.createType
import kotlin.reflect.full.isSubclassOf

class Fixture(val properties: MutableMap<String, Any> = mutableMapOf()) {

    fun chooseStrategy(clazz: KClass<*>): ObjectGenerator {
        if (clazz.createType().classifier in RandomExtensions.supportedTypes)
            return BasicTypeGenerator()

        if (clazz.isSubclassOf(Enum::class))
            return EnumGenerator()

        return ComplexObjectGenerator(properties)
    }

    companion object {

        inline fun <reified T> build(properties: MutableMap<String, Any> = mutableMapOf()): T {
            val fixture = Fixture(properties)
            val strategy = fixture.chooseStrategy(T::class)
            return strategy.execute(T::class) as T
        }
    }
}
