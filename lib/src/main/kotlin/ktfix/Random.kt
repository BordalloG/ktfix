package ktfix

import kotlin.random.Random
import kotlin.reflect.KType

class Random {
    companion object {
        fun random(type: KType): Any {
            return when (type.toString()) {
                "kotlin.String" -> randomString()
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

        private fun randomString(): String {
            val alphanumerics = "abcdefghiklmnopqrstuvwxyz0123456789"
            val length = Random.nextInt(10)
            return(1..length)
            .map { alphanumerics.random() }
                .joinToString("")
        }
    }
}
