package dev.brella.amber.sql.postgres.string.lexer.tokens

import dev.brella.amber.sql.postgres.PostgreSqlAppendable

public interface PostgreSqlLessThanAppendable<SELF : PostgreSqlLessThanAppendable<SELF>> : PostgreSqlAppendable<SELF> {
    public fun appendLessThan(): SELF = append('<')
    public fun appendLT(): SELF = append('<')
}

public inline fun <SELF : PostgreSqlLessThanAppendable<*>> SELF.lessThan(): SELF {
    appendLessThan()
    return this
}

public inline fun <SELF : PostgreSqlLessThanAppendable<*>> SELF.lt(): SELF {
    appendLT()
    return this
}