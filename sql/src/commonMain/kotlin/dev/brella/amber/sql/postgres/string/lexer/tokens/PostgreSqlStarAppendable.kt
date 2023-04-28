package dev.brella.amber.sql.postgres.string.lexer.tokens

import dev.brella.amber.sql.postgres.PostgreSqlAppendable

public interface PostgreSqlStarAppendable<SELF : PostgreSqlStarAppendable<SELF>> : PostgreSqlAppendable<SELF> {
    public fun appendStar(): SELF = append('*')
    public fun appendAsterisk(): SELF = append('*')
}

public inline fun <SELF : PostgreSqlStarAppendable<*>> SELF.star(): SELF {
    appendStar()
    return this
}

public inline fun <SELF : PostgreSqlStarAppendable<*>> SELF.asterisk(): SELF {
    appendAsterisk()
    return this
}