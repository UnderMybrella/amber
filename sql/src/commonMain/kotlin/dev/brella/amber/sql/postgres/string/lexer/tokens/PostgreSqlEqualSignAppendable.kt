package dev.brella.amber.sql.postgres.string.lexer.tokens

import dev.brella.amber.sql.postgres.PostgreSqlAppendable

public interface PostgreSqlEqualSignAppendable<SELF : PostgreSqlEqualSignAppendable<SELF>> :
    PostgreSqlAppendable<SELF> {
    public fun appendEqualSign(): SELF = append('=')
}

public inline fun <SELF : PostgreSqlEqualSignAppendable<*>> SELF.equalSign(): SELF {
    appendEqualSign()
    return this
}