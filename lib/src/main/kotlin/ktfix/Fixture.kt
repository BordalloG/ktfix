package ktfix

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import ktfix.extensions.RandomExtensions.Companion.nextType
import kotlin.random.Random
import kotlin.reflect.KCallable
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import ktfix.extensions.RandomExtensions


class Fixture {
    fun generateObjectOf(clazz: KClass<*>, properties: MutableMap<String, Any>): MutableMap<String, Any> {
        clazz.members.filter { it is KProperty && properties[it.name] == null }
            .map {
                properties[it.name] = generateValue(it)
            }
        return properties
    }

    private fun generateValue(element: KCallable<*>): Any {
        if (element.returnType.classifier in RandomExtensions.supportedTypes) {
            return Random.nextType(element.returnType)
        } else {
            if (element.returnType.classifier is KClass<*>) {
                return generateObjectOf(element.returnType.classifier as KClass<*>, mutableMapOf())
            }
            return true
        }
    }

    inline fun <reified T> convertValue(properties: MutableMap<String, Any>): T {
        val mapper = ObjectMapper().registerModule(KotlinModule.Builder().build())
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
