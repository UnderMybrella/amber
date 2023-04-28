package dev.brella.amber.sql.postgres.string.lexer.tokens

import dev.brella.amber.sql.postgres.PostgreSqlAppendable

public interface PostgreSqlRightShiftAppendable<SELF : PostgreSqlRightShiftAppendable<SELF>> :
    PostgreSqlAppendable<SELF> {
    public fun appendRightShift(): SELF = append(">>")
}

public inline fun <SELF : PostgreSqlRightShiftAppendable<*>> SELF.rightShift(): SELF {
    appendRightShift()
    return this
}