package ktfix

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import ktfix.extensions.RandomExtensions.Companion.nextBasicType
import kotlin.random.Random
import kotlin.reflect.KCallable
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class Fixture {
    companion object {
        private val kotlinBasicTypes = listOf(
            "kotlin.String",
            "kotlin.Char",
            "kotlin.Double",
            "kotlin.Float",
            "kotlin.Short",
            "kotlin.Long",
            "kotlin.Int",
            "kotlin.Byte",
            "kotlin.Boolean"
        )

        inline fun <reified T> build(properties: MutableMap<String, Any> = mutableMapOf()): T {
            generateObjectOf(T::class, properties)
            return convertValue(properties)
        }

        fun generateObjectOf(clazz: KClass<*>, properties: MutableMap<String, Any>): MutableMap<String, Any> {
            for (member in clazz.members) {
                if (member is KProperty) {
                    if (properties[member.name] == null) {
                        properties[member.name] = generateValue(member)
                    }
                }
            }
            return properties
        }

        private fun generateValue(element: KCallable<*>): Any {
            if (element.returnType.toString() in kotlinBasicTypes) {
                return Random.nextBasicType(element.returnType)
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
    }
}












val ala = "kazam"


