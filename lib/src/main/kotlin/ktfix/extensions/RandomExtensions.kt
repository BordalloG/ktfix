package ktfix.extensions

import kotlin.random.Random
import kotlin.reflect.KType

class RandomExtensions {
    companion object {
        private const val alphanumerics = "abcdefghiklmnopqrstuvwxyz0123456789";

        fun Random.nextBasicType(type: KType): Any {
            return when (type.toString().split(".").last()) {
                "String" -> Random.nextString()
                "Char" -> Random.nextChar()
                "Double" -> Random.nextDouble()
                "Float" -> Random.nextFloat()
                "Short" -> Random.nextInt().toShort()
                "Long" -> Random.nextLong()
                "Int" -> Random.nextInt()
                "Byte" -> Random.nextInt().toByte()
                "Boolean" -> Random.nextBoolean()
                else -> throw Exception("$type type not supported yet")
            }
        }

        fun Random.nextString(length: Int? = null): String {
            val range =
                if (length == null) 1..(1..31).random() else 1..length

            return(range)
                .map { alphanumerics.random() }
                .joinToString("")
        }

        fun Random.nextChar(): Char {
            return alphanumerics.random()
        }
    }
}
