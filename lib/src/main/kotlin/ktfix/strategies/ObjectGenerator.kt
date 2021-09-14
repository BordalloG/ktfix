package ktfix.strategies

import kotlin.reflect.KClass

interface ObjectGenerator {
    fun execute(clazz: KClass<*>) : Any
}