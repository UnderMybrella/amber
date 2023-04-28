package dev.brella.amber.sql.postgres.string.lexer.tokens

import dev.brella.amber.sql.SqlStringDsl
import dev.brella.amber.sql.postgres.PostgreSqlAppendable

public interface PostgreSqlSemicolonAppendable<SELF: PostgreSqlSemicolonAppendable<SELF>> : PostgreSqlAppendable<SELF> {
    public fun appendSemicolon(): SELF = append(';')
}

@SqlStringDsl
public inline fun <SELF : PostgreSqlSemicolonAppendable<*>> SELF.semicolon(): SELF {
    appendSemicolon()
    return this
}