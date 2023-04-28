package dev.brella.amber.sql.postgres.string.lexer.tokens

import dev.brella.amber.sql.postgres.PostgreSqlAppendable

public interface PostgreSqlLeftShiftAppendable<SELF : PostgreSqlLeftShiftAppendable<SELF>> :
    PostgreSqlAppendable<SELF> {
    public fun appendLeftShift(): SELF = append("<<")
}

public inline fun <SELF : PostgreSqlLeftShiftAppendable<*>> SELF.leftShift(): SELF {
    appendLeftShift()
    return this
}