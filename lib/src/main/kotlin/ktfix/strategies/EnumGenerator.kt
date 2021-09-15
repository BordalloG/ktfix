package ktfix.strategies

import ktfix.extensions.RandomExtensions.Companion.nextEnum
import kotlin.random.Random
import kotlin.reflect.KClass

class EnumGenerator : ObjectGenerator {
    override fun execute(clazz: KClass<*>): Any {
        return Random.nextEnum(clazz as KClass<out Enum<*>>)
    }
}