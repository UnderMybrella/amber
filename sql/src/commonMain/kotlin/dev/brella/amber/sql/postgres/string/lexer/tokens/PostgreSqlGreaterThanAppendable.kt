package dev.brella.amber.sql.postgres.string.lexer.tokens

import dev.brella.amber.sql.postgres.PostgreSqlAppendable

public interface PostgreSqlGreaterThanAppendable<SELF : PostgreSqlGreaterThanAppendable<SELF>> : PostgreSqlAppendable<SELF> {
    public fun appendGreaterThan(): SELF = append('>')
    public fun appendGT(): SELF = append('>')
}

public inline fun <SELF : PostgreSqlGreaterThanAppendable<*>> SELF.greaterThan(): SELF {
    appendGreaterThan()
    return this
}

public inline fun <SELF : PostgreSqlGreaterThanAppendable<*>> SELF.gt(): SELF {
    appendGT()
    return this
}