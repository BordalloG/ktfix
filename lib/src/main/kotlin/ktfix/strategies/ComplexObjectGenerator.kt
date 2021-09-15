package ktfix.strategies

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import ktfix.extensions.RandomExtensions
import ktfix.extensions.RandomExtensions.Companion.nextEnum
import ktfix.extensions.RandomExtensions.Companion.nextType
import kotlin.random.Random
import kotlin.reflect.KCallable
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.isSubclassOf

class ComplexObjectGenerator : ObjectGenerator {
    override fun execute(clazz: KClass<*>): Any {
        var properties = mutableMapOf<String, Any>()
        clazz.members.filter { it is KProperty && properties[it.name] == null }
            .map {
                properties[it.name] = generateValue(it)
            }
        return convertValue(clazz, properties)
    }

    @Suppress("UNCHECKED_CAST")
    private fun generateValue(element: KCallable<*>): Any {
        return if (element.returnType.classifier in RandomExtensions.supportedTypes) {
            Random.nextType(element.returnType)
        } else {
            val elementClazz = element.returnType.classifier as KClass<*>

            if (elementClazz.isSubclassOf(Enum::class)) {
                return Random.nextEnum(elementClazz as KClass<out Enum<*>>)
            } else {
                execute(elementClazz)
            }
        }
    }

    private fun <T> convertValue(clazz: KClass<*>, properties: MutableMap<String, Any>): T {
        val mapper = ObjectMapper().registerModule(KotlinModule.Builder().build()).registerModule(JavaTimeModule())
        return mapper.convertValue(properties, clazz.java) as T
    }
}
