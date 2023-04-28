package dev.brella.amber.sql.postgres.string.lexer.tokens

import dev.brella.amber.sql.postgres.PostgreSqlAppendable

public interface PostgreSqlLessEqualsAppendable<SELF : PostgreSqlLessEqualsAppendable<SELF>> :
    PostgreSqlAppendable<SELF> {
    public fun appendLessEquals(): SELF = append("<=")
    public fun appendLessThanEqualTo(): SELF = append("<=")
}

public inline fun <SELF : PostgreSqlLessEqualsAppendable<*>> SELF.lessEquals(): SELF {
    appendLessEquals()
    return this
}

public inline fun <SELF : PostgreSqlLessEqualsAppendable<*>> SELF.lessThanEqualTo(): SELF {
    appendLessThanEqualTo()
    return this
}