package dev.brella.amber.sql.postgres.string.lexer.tokens

import dev.brella.amber.sql.postgres.PostgreSqlAppendable

public interface PostgreSqlDotAppendable<SELF : PostgreSqlDotAppendable<SELF>> : PostgreSqlAppendable<SELF> {
    public fun appendDot(): SELF = append('.')
}

public inline fun <SELF : PostgreSqlDotAppendable<*>> SELF.dot(): SELF {
    appendDot()
    return this
}