package dev.brella.amber.sql.postgres.string.lexer.keywords

import kotlin.jvm.JvmInline

@JvmInline
public value class PostgreSqlKeywordStatus(public val flags: Int) {
    public companion object {
        public const val RESERVED_MASK: Int = 1 shl 0
        public const val CAN_BE_FUNCTION_OR_TYPE_MASK: Int = 1 shl 1
        public const val REQUIRES_AS_MASK: Int = 1 shl 2

        public const val NON_RESERVED: Int = 0 or CAN_BE_FUNCTION_OR_TYPE_MASK
        public const val NON_RESERVED_REQUIRES_AS: Int = NON_RESERVED or REQUIRES_AS_MASK
        public const val NON_RESERVED_CANNOT_BE_FUNCTION_OR_TYPE: Int = 0
        public const val NON_RESERVED_CANNOT_BE_FUNCTION_OR_TYPE_REQUIRES_AS: Int =
            NON_RESERVED_CANNOT_BE_FUNCTION_OR_TYPE or REQUIRES_AS_MASK

        public const val RESERVED: Int = RESERVED_MASK
        public const val RESERVED_REQUIRES_AS: Int = RESERVED or REQUIRES_AS_MASK
        public const val RESERVED_CAN_BE_FUNCTION_OR_TYPE: Int = RESERVED or CAN_BE_FUNCTION_OR_TYPE_MASK
        public const val RESERVED_CAN_BE_FUNCTION_OR_TYPE_REQUIRES_AS: Int =
            RESERVED_CAN_BE_FUNCTION_OR_TYPE or REQUIRES_AS_MASK

        public fun nonReserved(): PostgreSqlKeywordStatus =
            PostgreSqlKeywordStatus(NON_RESERVED)

        public fun nonReservedRequiresAs(): PostgreSqlKeywordStatus =
            PostgreSqlKeywordStatus(NON_RESERVED_REQUIRES_AS)

        public fun nonReservedCannotBeFunctionOrType(): PostgreSqlKeywordStatus =
            PostgreSqlKeywordStatus(NON_RESERVED_CANNOT_BE_FUNCTION_OR_TYPE)

        public fun nonReservedCannotBeFunctionOrTypeRequiresAs(): PostgreSqlKeywordStatus =
            PostgreSqlKeywordStatus(NON_RESERVED_CANNOT_BE_FUNCTION_OR_TYPE_REQUIRES_AS)

        public fun reserved(): PostgreSqlKeywordStatus =
            PostgreSqlKeywordStatus(RESERVED)

        public fun reservedRequiresAs(): PostgreSqlKeywordStatus =
            PostgreSqlKeywordStatus(RESERVED_REQUIRES_AS)

        public fun reservedCanBeFunctionOrType(): PostgreSqlKeywordStatus =
            PostgreSqlKeywordStatus(RESERVED_CAN_BE_FUNCTION_OR_TYPE)

        public fun reservedCanBeFunctionOrTypeRequiresAs(): PostgreSqlKeywordStatus =
            PostgreSqlKeywordStatus(RESERVED_CAN_BE_FUNCTION_OR_TYPE_REQUIRES_AS)

        private inline fun Int.withFlag(bitflag: Int, enabled: Boolean?): Int =
            when (enabled) {
                true -> this or bitflag
                false -> this and bitflag.inv()
                null -> this
            }

        public fun build(
            isReserved: Boolean? = null,
            requiresAs: Boolean? = null,
            canBeFunctionOrType: Boolean? = null,
        ): PostgreSqlKeywordStatus =
            PostgreSqlKeywordStatus(
                0
                    .withFlag(RESERVED_MASK, isReserved)
                    .withFlag(REQUIRES_AS_MASK, requiresAs)
                    .withFlag(CAN_BE_FUNCTION_OR_TYPE_MASK, canBeFunctionOrType)
            )
    }

    public infix fun hasFlag(value: Int): Boolean = flags and value == value
    public infix fun withFlag(value: Int): PostgreSqlKeywordStatus =
        PostgreSqlKeywordStatus(flags or value)

    public val isReserved: Boolean get() = hasFlag(RESERVED_MASK)
    public val requiresAs: Boolean get() = hasFlag(REQUIRES_AS_MASK)
    public val canBeFunctionOrType: Boolean get() = hasFlag(CAN_BE_FUNCTION_OR_TYPE_MASK)
}