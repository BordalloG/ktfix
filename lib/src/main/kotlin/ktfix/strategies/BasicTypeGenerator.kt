package ktfix.strategies

import ktfix.extensions.RandomExtensions.Companion.nextType
import kotlin.random.Random
import kotlin.reflect.KClass
import kotlin.reflect.full.createType

class BasicTypeGenerator : ObjectGenerator {
    override fun execute(clazz: KClass<*>): Any {
        return Random.nextType(clazz.createType())
    }
}