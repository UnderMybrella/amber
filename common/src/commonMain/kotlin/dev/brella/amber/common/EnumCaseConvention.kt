package dev.brella.amber.common

import dev.brella.kornea.base.common.LazySpan
import dev.brella.kornea.base.common.lazySpan

public enum class EnumCaseConvention(private val transform: (String) -> String) {
    UPPERCASE(String::uppercase),
    LOWERCASE(String::lowercase),
    PASCAL_CASE({ str ->
        str.splitAndJoin(" " to "", "-" to "-", "_" to "", transform = String::titlecase)
    }),
    CAMEL_CASE({ str ->
        str.splitAndJoin(" " to "", "-" to "-", "_" to "", transform = String::titlecase)
            .replaceFirstChar(Char::lowercaseChar)
    }),
    SNAKE_CASE({ str ->
        str.splitAndJoin(" " to "_", "-" to "-", "_" to "_", transform = String::lowercase)
    }),
    SCREAMING_SNAKE_CASE({ str ->
        str.splitAndJoin(" " to "_", "-" to "-", "_" to "_", transform = String::uppercase)
    }),
    CAMEL_SNAKE_CASE({ str ->
        str.splitAndJoin(" " to "_", "-" to "-", "_" to "_", transform = String::titlecase)
            .replaceFirstChar(Char::lowercaseChar)
    }),
    KEBAB_CASE({ str ->
        str.splitAndJoin(" " to "-", "-" to "-", "_" to "-", transform = String::lowercase)
    }),
    SCREAMING_KEBAB_CASE({ str ->
        str.splitAndJoin(" " to "-", "-" to "-", "_" to "-", transform = String::uppercase)
    }),
    TRAIN_CASE({ str ->
        str.splitAndJoin(" " to "-", "-" to "-", "_" to "_", transform = String::titlecase)
    }),
    SPONGE_CASE({ str ->
        val array = str.toCharArray()
        repeat(array.size) { i ->
            array[i] = if (i and 1 == 0)
                array[i].uppercaseChar()
            else
                array[i].lowercaseChar()
        }
        array.concatToString()
    }),

    ;

    public operator fun invoke(keyword: String): String =
        transform(keyword)

    public companion object {
        public fun transform(keyword: String): Array<String> =
            values().let { values -> Array(values.size) { values[it](keyword) } }

        public fun transformLazy(keyword: String): LazySpan<String> =
            values().let { values -> lazySpan(values.size) { values[it](keyword) } }
    }
}