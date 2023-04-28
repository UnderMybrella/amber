package dev.brella.amber.sql.postgres.string.lexer.tokens

import dev.brella.amber.sql.postgres.PostgreSqlAppendable

public interface PostgreSqlGreaterEquals<SELF : PostgreSqlGreaterEquals<SELF>> : PostgreSqlAppendable<SELF> {
    public fun appendGreaterEquals(): SELF = append(">=")
    public fun appendGreaterThanEqualTo(): SELF = append(">=")
}

public inline fun <SELF : PostgreSqlGreaterEquals<*>> SELF.greaterEquals(): SELF {
    appendGreaterEquals()
    return this
}

public inline fun <SELF : PostgreSqlGreaterEquals<*>> SELF.greaterThanEqualTo(): SELF {
    appendGreaterThanEqualTo()
    return this
}