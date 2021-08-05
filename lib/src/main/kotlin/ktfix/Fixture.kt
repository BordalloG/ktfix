package ktfix

import kotlin.reflect.KProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import ktfix.Random.Companion.random
import kotlin.reflect.KCallable
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredMembers


class Fixture {
    companion object {
        inline fun <reified T> build(properties: MutableMap<String, Any> = mutableMapOf()): T {
            generateFakeObject(membersOf<T>(), properties)
            return convertValue(properties)
        }

        fun generateFakeObject(
            elements: Collection<KCallable<*>>,
            properties: MutableMap<String, Any>
        ): MutableMap<String, Any> {
            for (element in elements) {
                if (element is KProperty) {
                    if (properties[element.name] == null) {
                        properties[element.name] = generateValue(element)
                    }
                }
            }
            return properties
        }

        private fun generateValue(element: KCallable<*>) : Any {
            if (element.returnType.toString().contains("kotlin")) {
                return random(element.returnType)
            } else {
                if (element.returnType.classifier is KClass<*>) {
                    return generateFakeObject(membersOf(element.returnType.classifier as KClass<*>) ,mutableMapOf())
                }
                return true
            }
        }



        inline fun <reified T> convertValue(properties: MutableMap<String, Any>): T {
            val mapper = ObjectMapper().registerModule(KotlinModule.Builder().build())
            return mapper.convertValue(properties, T::class.java)
        }

        private fun membersOf(element: KClass<*>) : Collection<KCallable<*>>  {
            return element.members
        }
        inline fun <reified T> membersOf() : Collection<KCallable<*>>  {
            return T::class.members
        }

    }
}