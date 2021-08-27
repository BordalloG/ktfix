package ktfix

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

class Fixture {
    fun generateObjectOf(clazz: KClass<*>, properties: MutableMap<String, Any>): MutableMap<String, Any> {
        clazz.members.filter { it is KProperty && properties[it.name] == null }
            .map {
                properties[it.name] = generateValue(it)
            }
        return properties
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
                generateObjectOf(elementClazz, mutableMapOf())
            }
        }
    }

    inline fun <reified T> convertValue(properties: MutableMap<String, Any>): T {
        val mapper = ObjectMapper().registerModule(KotlinModule.Builder().build()).registerModule(JavaTimeModule())
        return mapper.convertValue(properties, T::class.java)
    }

    companion object {
        val fixture = Fixture()

        inline fun <reified T> build(properties: MutableMap<String, Any> = mutableMapOf()): T {
            fixture.generateObjectOf(T::class, properties)
            return fixture.convertValue(properties)
        }
    }
}
