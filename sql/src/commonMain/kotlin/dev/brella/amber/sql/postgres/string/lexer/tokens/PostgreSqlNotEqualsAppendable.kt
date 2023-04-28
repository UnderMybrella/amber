package dev.brella.amber.sql.postgres.string.lexer.tokens

import dev.brella.amber.sql.postgres.PostgreSqlAppendable

public interface PostgreSqlNotEqualsAppendable<SELF : PostgreSqlNotEqualsAppendable<SELF>> :
    PostgreSqlAppendable<SELF> {
    public fun appendNotEquals(): SELF = append("<>")
}

public inline fun <SELF : PostgreSqlNotEqualsAppendable<*>> SELF.notEquals(): SELF {
    appendNotEquals()
    return this
}