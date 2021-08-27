package ktfix.extensions

import java.time.LocalDate
import java.time.LocalDate.ofEpochDay
import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlin.random.Random
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.createType

class RandomExtensions {
    companion object {

        private val typeToRandom = mapOf(
            String::class.createType().classifier to Random.nextString(),
            Char::class.createType().classifier to Random.nextChar(),
            Boolean::class.createType().classifier to Random.nextBoolean(),
            Double::class.createType().classifier to Random.nextDouble(),
            Float::class.createType().classifier to Random.nextFloat(),
            Short::class.createType().classifier to Random.nextInt().toShort(),
            Long::class.createType().classifier to Random.nextLong(),
            Int::class.createType().classifier to Random.nextInt(),
            Byte::class.createType().classifier to Random.nextInt().toByte(),
            LocalDate::class.createType().classifier to Random.nextLocalDate(),
            LocalDateTime::class.createType().classifier to Random.nextLocalDateTime()
        )

        val supportedTypes = typeToRandom.keys

        private const val alphanumerics = "abcdefghiklmnopqrstuvwxyz0123456789"

        fun Random.nextType(type: KType): Any {
            val value = typeToRandom[type.classifier]
            if (value != null) return value
            throw Exception("$type type not supported yet")
        }

        fun Random.nextString(length: Int? = null): String {
            val range =
                if (length == null) 1..(1..31).random() else 1..length

            return (range)
                .map { alphanumerics.random() }
                .joinToString("")
        }

        fun Random.nextChar(): Char {
            return alphanumerics.random()
        }

        fun Random.nextLocalDate(
            startDate: LocalDate = LocalDate.MIN,
            endDate: LocalDate = LocalDate.MAX
        ): LocalDate {
            if (startDate > endDate) throw IllegalArgumentException("endDate cannot be after startDate")
            return LocalDate.ofEpochDay(nextLong(startDate.toEpochDay(), endDate.toEpochDay()))
        }

        fun Random.nextLocalDateTime(
            startDate: LocalDateTime = LocalDateTime.MIN,
            endDate: LocalDateTime = LocalDateTime.MAX,
            zoneOffSet: ZoneOffset = ZoneOffset.UTC
        ): LocalDateTime {
            if (startDate > endDate) throw IllegalArgumentException("endDate cannot be after startDate")

            return LocalDateTime.ofEpochSecond(
                nextLong(
                    startDate.toEpochSecond(zoneOffSet),
                    endDate.toEpochSecond(zoneOffSet)
                ),
                0, zoneOffSet
            )
        }

        fun Random.nextEnum(enumType: KClass<out Enum<*>>): Any {
            val enumValues = enumType.java.enumConstants
            return enumValues[Random.nextInt(0, enumValues.size)]
        }
    }
}
