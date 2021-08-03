package ktfix

import kotlin.reflect.KProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import kotlin.reflect.KType
import kotlin.reflect.full.declaredMembers


class Fixture {
    companion object {
        fun random(type: KType): Any {
            type.classifier
            return when (type.toString()) {
                "kotlin.String" -> "something"
                "kotlin.Char" -> 'c'
                "kotlin.Double" -> 0.5
                "kotlin.Float" -> 0.5f
                "kotlin.Short" -> 1
                "kotlin.Long" -> 1
                "kotlin.Int" -> 1
                "kotlin.Byte" -> 1
                "kotlin.Boolean" -> true
                else -> throw Exception("$type type not supported yet")
            }
        }

        inline fun <reified T> build(properties: MutableMap<String, Any> = mutableMapOf()): T {
            val mapper = ObjectMapper().registerModule(KotlinModule.Builder().build())

            for (element in T::class.declaredMembers) {
                if(element is KProperty) {
                    if (properties[element.name] == null) {
                        properties[element.name] = random(element.returnType)
                    }
                }
            }
            return mapper.convertValue(properties, T::class.java)
        }
    }
}